package com.withdog.cart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.withdog.cart.dto.CartDTO;

public class CartCookieUtils {

    public static void addToCart(HttpServletRequest request, HttpServletResponse response, CartDTO cartDTO) {
        // 기존 쿠키 가져오기
        Cookie[] cookies = request.getCookies();

        // 장바구니 쿠키 생성 또는 가져오기
        Cookie cartCookie = findCartCookie(cookies);

        // 새로운 상품 정보를 현재 장바구니 값에 추가
        String updatedCartValue = updateCartValue(cartCookie, cartDTO);

        // 업데이트된 장바구니 쿠키 생성
        Cookie updatedCartCookie = createCartCookie(updatedCartValue);

        response.addCookie(updatedCartCookie);
    }
    


    private static Cookie findCartCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Cart")) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private static String updateCartValue(Cookie cartCookie, CartDTO cartDTO) {
        String currentCartValue = (cartCookie != null) ? cartCookie.getValue() : "";

        // 이미 해당 상품이 장바구니에 있는지 확인
        if (!containsProduct(currentCartValue, cartDTO)) {
            // 상품 정보를 추가
            String productInfo = cartDTO.getProductId() + ":" + cartDTO.getProductName() + ":" + cartDTO.getProductBrand() + ":" + cartDTO.getCount() + ":" + cartDTO.getPrice();
            currentCartValue = currentCartValue + "|" + productInfo;
        }

        return currentCartValue;
    }

    private static boolean containsProduct(String cartValue, CartDTO cartDTO) {
        // 장바구니 값에 해당 상품이 있는지 확인
        String[] products = cartValue.split("\\|");
        for (String product : products) {
            String[] parts = product.split(":");
            if (parts.length == 5 &&
            	Integer.parseInt(parts[0]) == cartDTO.getProductId() &&
                parts[1].equals(cartDTO.getProductName()) &&
                parts[2].equals(cartDTO.getProductBrand()) &&
                Integer.parseInt(parts[3]) == cartDTO.getCount() &&
                Integer.parseInt(parts[4]) == cartDTO.getPrice()) {
                return true;
            }
        }
        return false;
    }

    public static Cookie createCartCookie(String cartValue) {
        // 장바구니 쿠키 생성 및 설정
        Cookie cartCookie = new Cookie("Cart", cartValue);
        cartCookie.setMaxAge(30 * 24 * 60 * 60);  // 쿠키 유효기간 설정 초단위
        cartCookie.setPath("/");  // 쿠키의 경로 설정
        return cartCookie;
    }


    public static void clearCartCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("Cart", null);
        cookie.setMaxAge(0); // 쿠키 유효 시간을 0으로 설정하여 삭제 따로 삭제 함수는 없음
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
