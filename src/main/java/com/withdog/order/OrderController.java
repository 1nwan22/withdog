package com.withdog.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
@Controller
public class OrderController {

	// http://localhost/order/recent-history-view
		@GetMapping("/recent-history-view")
		public String orderHistoryView(Model model) {
			model.addAttribute("viewName", "order/recentHistory");
			model.addAttribute("viewNameR", "include/rightSide");
			return "template/layout";
		}
	
	// http://localhost/order/checkout-view
	@GetMapping("/checkout-view")
	public String checkoutView(Model model) {
		model.addAttribute("viewName", "order/checkout");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
}
