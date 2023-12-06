package com.withdog.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.withdog.cart.bo.CartBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class CartController {

	private final CartBO cartBO;
	
	// http://localhost/cart
	@GetMapping("/cart-view")
	public String cartView(Model model, @CookieValue(value = "Cart", defaultValue = "") String cartCookieValue) {
		log.info("$$$$$$$$$$$ cartCookieValue = {}", cartCookieValue);
		model.addAttribute("viewName", "cart/cart");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
}
