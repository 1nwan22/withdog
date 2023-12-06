package com.withdog.cart;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.util.ObjectUtils;

import com.withdog.cart.dto.CartDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartCookieUtils {

	@Transactional
	public static String addToCart(HttpServletRequest request, HttpServletResponse response, CartDTO cartDTO) {
		// 기존 쿠키 가져오기
		Cookie[] cookies = request.getCookies();

		// 장바구니 쿠키 생성 또는 가져오기
		Cookie cartCookie = findCartCookie(cookies);

		// 새로운 상품 정보를 현재 장바구니 값에 추가
		String updatedCartValue = updateCartValue(cartCookie, cartDTO);

		// 업데이트된 장바구니 쿠키 생성
		Cookie updatedCartCookie = createCartCookie(updatedCartValue);

		log.info("$$$$$$$$$$$$$$$ CartCookie = {}", updatedCartCookie.toString());
		response.addCookie(updatedCartCookie);
		
		return updatedCartValue;
	}
	
	@Transactional
	public static void deleteInCart(HttpServletRequest request, HttpServletResponse response, int productId) {
		// 기존 쿠키 가져오기
		Cookie[] cookies = request.getCookies();

		// 장바구니 쿠키 생성 또는 가져오기
		Cookie cartCookie = findCartCookie(cookies);
		
		String cartValue = (cartCookie != null) ? cartCookie.getValue() : null;
		
		String[] products = cartValue.split("\\|");
		StringBuilder cartValueBuilder = new StringBuilder();
		
		for (String product : products) {
			String[] parts = product.split(":");
			int parsedProductId = Integer.parseInt(parts[0]);
			
			if (parsedProductId == productId) { // 타겟 상품 삭제
				product = "";
			}
			
			// StringBuilder에 추가
			cartValueBuilder.append(product).append("|");
		}
		// 마지막의 "|" 제거
		if (cartValueBuilder.length() > 0) {
			cartValueBuilder.setLength(cartValueBuilder.length() - 1);
		}
		
		String updatedCartValue = cartValueBuilder.toString();
		
		Cookie updatedCartCookie = createCartCookie(updatedCartValue);
		log.info("$$$$$$$$$$$$$$$ CartCookie = {}", updatedCartCookie);

		response.addCookie(updatedCartCookie);
	}
	
	@Transactional
	public static void clearCartCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("Cart", null);
		cookie.setMaxAge(0); // 쿠키 유효 시간을 0으로 설정하여 삭제 따로 삭제 함수는 없음
		cookie.setPath("/");
		response.addCookie(cookie);
	}


	public static Cookie findCartCookie(Cookie[] cookies) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Cart")) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	public static Map<Integer, Integer> productIdAndCountCartCookie(HttpServletRequest request, String cartCookieValue) {
		Map<Integer, Integer> productIdAndCount = new HashMap<>();
		String[] products = cartCookieValue.split("\\|");
				
		for (String product : products) {
			String[] parts = product.split(":");
			int productId = Integer.parseInt(parts[0]);
			int count = Integer.parseInt(parts[4]);
			productIdAndCount.put(productId, count);
		}
		
		return productIdAndCount;
	}

	private static String updateCartValue(Cookie cartCookie, CartDTO cartDTO) {
		String cartValue = (cartCookie != null) ? cartCookie.getValue() : null;
		StringBuilder cartValueBuilder = new StringBuilder();

		// 장바구니 쿠키 처음 생성
		if (ObjectUtils.isEmpty(cartValue)) {
			cartValueBuilder.append(cartDTO.getProductId()).append(":").append(cartDTO.getProductBrand()).append(":")
			.append(cartDTO.getProductName()).append(":").append(cartDTO.getPrice()).append(":").append(cartDTO.getCount());
			
			return cartValueBuilder.toString();
		}

		
		if (!containsProduct(cartValue, cartDTO)) {
			cartValueBuilder.append(cartDTO.getProductId()).append(":").append(cartDTO.getProductBrand()).append(":")
			.append(cartDTO.getProductName()).append(":").append(cartDTO.getPrice()).append(":").append(cartDTO.getCount()).append("|");
			
			return cartValueBuilder.append(cartValue).toString();
		}
		String[] products = cartValue.split("\\|");

		for (String product : products) {
			String[] parts = product.split(":");
			int productId = Integer.parseInt(parts[0]);
			String productBrand = parts[1];
			String productName = parts[2];
			int productPrice = Integer.parseInt(parts[3]);
			int count = Integer.parseInt(parts[4]);

			if (productId == cartDTO.getProductId()) { // 기존 상품 업데이트
				// 기존 상품은 수량 수정
				count += cartDTO.getCount();
				// StringBuilder에 추가
			}
			cartValueBuilder.append(productId).append(":").append(productBrand).append(":")
			.append(productName).append(":").append(productPrice).append(":").append(count).append("|");
		}
		
		// 마지막의 "|" 제거
        if (cartValueBuilder.length() > 0) {
        	cartValueBuilder.setLength(cartValueBuilder.length() - 1);
        }
		
		return cartValueBuilder.toString();
	}
	
	 private static boolean containsProduct(String cartValue, CartDTO cartDTO) {
		 // 장바구니 값에 해당 상품이 있는지 확인
	    	
		 String[] products = cartValue.split("\\|");
		 for (String product : products) {
			 String[] parts = product.split(":");
	
			 if (Integer.parseInt(parts[0]) == cartDTO.getProductId()) {
				 return true;
			 }
		 }
		 return false;
	 }
	        

	private static Cookie createCartCookie(String cartValue) {
		// 장바구니 쿠키 생성 및 설정
		Cookie cartCookie = new Cookie("Cart", cartValue);
		cartCookie.setMaxAge(30 * 24 * 60 * 60); // 쿠키 유효기간 설정 초단위 (30일)
		cartCookie.setPath("/"); // 쿠키의 경로 설정
		return cartCookie;
	}

}
