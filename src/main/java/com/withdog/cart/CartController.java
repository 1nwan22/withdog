package com.withdog.cart;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.withdog.cart.bo.CartBO;
import com.withdog.cart.dto.CartView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class CartController {

	private final CartBO cartBO;
	
	// http://localhost/cart
	@GetMapping("/cart")
	public String cartView(Model model, @CookieValue(value = "Cart", defaultValue = "") String cartCookieValue) {
		log.info("$$$$$$$$$$$ cartCookieValue = {}", cartCookieValue);
		List<CartView> cartList = cartBO.getCartViewListByCookieValue(cartCookieValue);
		model.addAttribute("cartList", cartList);
		model.addAttribute("viewName", "cart/cart");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
}
