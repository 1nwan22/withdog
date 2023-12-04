package com.withdog.cart;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@RequestMapping("/cart")
@RestController
public class CartRestController {

	@GetMapping("/add")
	public Map<String, Object> addCart(
			HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam int productId,
			@RequestParam int quantity) {
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie = WebUtils.getCookie(request, "cartCookie");
        StringBuilder cartValueBuilder = new StringBuilder();
        
        
        // 비로그인 & 쿠키 없음
        
        
        // 비로그인 & 쿠키 있음
        
        // 로그인 -> db에서 카트 조회해서 담기
        
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		return result;
	}
}
