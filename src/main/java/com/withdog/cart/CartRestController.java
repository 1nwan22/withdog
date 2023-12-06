package com.withdog.cart;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.account.bo.AccountBO;
import com.withdog.cart.bo.CartBO;
import com.withdog.cart.dto.CartDTO;
import com.withdog.product.bo.ProductBO;
import com.withdog.product.entity.ProductEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartRestController {

    private final ProductBO productBO;
    private final CartBO cartBO;
    private final AccountBO accountBO;

    @PostMapping("/add")
    public Map<String, Object> addCart(
            HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestParam int productId,
            @RequestParam int count) {
        log.info("$$$$$$$$$$ productId = {} , count = {}", productId, count);
        Map<String, Object> result = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        ProductEntity productEntity = productBO.getProductById(productId);
        String productName = productEntity.getName();
        String productBrand = productEntity.getBrand();
        int productPrice = productEntity.getPrice();
        String email = (String) session.getAttribute("email");

        // 비로그인 상태이면서 쿠키가 있는 경우에는 쿠키를 호출하고 추가 상품만 처리
        if (email == null && cookies != null) {
            String cartCookieValue = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Cart")) {
                    cartCookieValue = cookie.getValue();
                    log.info("$$$$$$$$$$$$ cartCookieValue = {}", cartCookieValue);
                }
            }

            if (cartCookieValue != null) {
                String[] products = cartCookieValue.split("\\|");
                StringBuilder updatedCartValue = new StringBuilder();
                
                
                for (String product : products) {
                    String[] parts = product.split(":");
                    if (parts.length == 5) {
                    	int parsedProductId = Integer.parseInt(parts[0]);
                    	String parsedProductName = parts[1];
                    	String parsedProductBrand = parts[2];
                        int parsedCount = Integer.parseInt(parts[3]);
                        int parsedProductPrice = Integer.parseInt(parts[4]);

                        if (parsedProductId == productId) {
                            // 기존 상품은 수량 처리
                            parsedCount += count;
                            // 기존 상품의 정보를 수정
                            product = parsedProductId + ":" + parsedProductName + ":" + parsedProductBrand + ":" + parsedCount + ":" + parsedProductPrice;
                            
                            // StringBuilder에 업데이트된 상품 정보 추가
                            updatedCartValue.append(product).append("|");
                            
                            // 마지막의 "|" 제거
                            if (updatedCartValue.length() > 0) {
                                updatedCartValue.setLength(updatedCartValue.length() - 1);
                            }

                            // 업데이트된 장바구니 쿠키 생성
                            Cookie updatedCartCookie = CartCookieUtils.createCartCookie(updatedCartValue.toString());
                            response.addCookie(updatedCartCookie);
                      
                        } else {
                            // 새로운 상품
                            CartDTO cartDTO = new CartDTO(productId, productName, productBrand, count, productPrice);
                            CartCookieUtils.addToCart(request, response, cartDTO);
                        }
                    }
                }
            } else {
            	CartDTO cartDTO = new CartDTO(productId, productName, productBrand, count, productPrice);
                CartCookieUtils.addToCart(request, response, cartDTO);
            }
        } else if (email== null && cookies == null) {
            // 비로그인 && 쿠키 없음
        	CartDTO cartDTO = new CartDTO(productId, productName, productBrand, count, productPrice);
            CartCookieUtils.addToCart(request, response, cartDTO);

        
        } else if (email != null) {
        	// 로그인 > cart db 저장
        	int accountId = accountBO.getAccountEntityByEmail(email).getId();
            cartBO.addCart(accountId, productId, count, productPrice);
            CartDTO cartDTO = new CartDTO(productId, productName, productBrand, count, productPrice);
            CartCookieUtils.addToCart(request, response, cartDTO);
        }

        result.put("code", 200);
        result.put("result", "success");
        return result;
    }
}
