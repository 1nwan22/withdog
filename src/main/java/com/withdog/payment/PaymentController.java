package com.withdog.payment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

	// http://localhost/checkout-view
	@GetMapping("/checkout-view")
	public String checkoutView(Model model) {
		model.addAttribute("viewName", "pay/checkout");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
}
