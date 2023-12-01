package com.withdog.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

	// http://localhost/cart
	@GetMapping("/cart")
	public String cartView(Model model) {
		model.addAttribute("viewName", "cart/cart");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
}
