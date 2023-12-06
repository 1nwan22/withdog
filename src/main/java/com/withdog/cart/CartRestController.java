package com.withdog.cart;

import java.util.HashMap;
import java.util.List;
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

	private final CartBO cartBO;
	private final ProductBO productBO;

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
        Integer accountId = (Integer) session.getAttribute("accountId");
        
        if (ObjectUtils.isEmpty(accountId)) { // 비로그인 상태
        	CartDTO cartDTO = new CartDTO(productId, productBrand, productName, productPrice, count);
            log.info("$$$$$$$$$$$$$$$$ cartDTO = {}", cartDTO);
            CartCookieUtils.addToCart(request, response, cartDTO);
            result.put("code", 200);
            result.put("result", "success logout");
            return result;
        }
        
        if (!ObjectUtils.isEmpty(accountId)) { // 로그인 상태
        	List<CartDTO> cartList = cartBO.getCartListByAccountId(accountId);
        	cartList.forEach(cart -> CartCookieUtils.addToCart(request, response, cart));
        	CartDTO cartDTO = new CartDTO(productId, productBrand, productName, productPrice, count);
        	String cartCookieValue = CartCookieUtils.addToCart(request, response, cartDTO);
//        	Cookie cartCookie = CartCookieUtils.findCartCookie(request.getCookies());
//        	log.info("$$$$$$$$$$$$$$$$ cartCookie = {}", cartCookie);
//        	cartBO.addCart(accountId, cartCookie);
        	Map<Integer, Integer> productIdAndCount = CartCookieUtils.productIdAndCountCartCookie(request, cartCookieValue);
        	log.info("$$$$$$$$$$$$$$$$ productIdAndCount = {}", productIdAndCount);
        	cartBO.addCart(accountId, productIdAndCount);
        }

        result.put("code", 200);
        result.put("result", "success login");
        return result;
    }
}
