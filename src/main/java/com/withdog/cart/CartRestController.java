package com.withdog.cart;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.account.bo.AccountBO;
import com.withdog.cart.bo.CartBO;
import com.withdog.cart.dto.CartDTO;
import com.withdog.cart.entity.CartEntity;
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
        ProductEntity productEntity = productBO.getProductById(productId);
        String productName = productEntity.getName();
        String productBrand = productEntity.getBrand();
        int productPrice = productEntity.getPrice();
        CartEntity cartEntity = cartBO.getCartByProductId(productId);
        String email = (String) session.getAttribute("email");
        int accountId = 0;
        if (!ObjectUtils.isEmpty(email)) {
        	accountId = accountBO.getAccountEntityByEmail(email).getId();
        	if (!ObjectUtils.isEmpty(cartEntity)) { // 로그인 O DB에 상품이 있는 경우 수량 업데이트
        		count += cartEntity.getCount();
        	}
        }
        
        CartDTO cartDTO = new CartDTO(productId, productName, productBrand, count, productPrice);
        CartCookieUtils.addToCart(request, response, cartDTO);
        
        if (!ObjectUtils.isEmpty(email)) {
        	cartBO.addCart(accountId, cartDTO);
        }

        result.put("code", 200);
        result.put("result", "success");
        return result;
    }
}
