package com.withdog.cart;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.withdog.cart.bo.CartBO;
import com.withdog.cart.domain.CartDTO;
import com.withdog.cart.domain.CartView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class CartController {

	private final CartBO cartBO;
	
	// http://localhost/cart
	@GetMapping("/cart")
	public String cartView(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@CookieValue(value = "Cart", defaultValue = "") String cartCookieValue) {
		log.info("$$$$$$$$$$$ cartCookieValue = {}", cartCookieValue);
		Integer accountId = (Integer) session.getAttribute("accountId");
		
		if (ObjectUtils.isEmpty(accountId)) { // 비로그인
			return "redirect://sign-in-view";
		}
		if (!ObjectUtils.isEmpty(accountId)) { // 로그인 상태
        	List<CartDTO> cartList = cartBO.getCartDTOListByAccountId(accountId);
        	if (!ObjectUtils.isEmpty(cartList)) {
        		cartList.forEach(cart -> CartCookieUtils.addToCart(request, response, cart));
        	}
        	cartCookieValue = CartCookieUtils.getCartCookieValue(request);
        	List<CartView> cartViewList = cartBO.getCartViewListByCookieValue(cartCookieValue);
        	model.addAttribute("cartList", cartViewList);
        }
		
		model.addAttribute("viewName", "cart/cart");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
}
