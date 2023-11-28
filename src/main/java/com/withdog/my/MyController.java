package com.withdog.my;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/my")
@Controller
public class MyController {

	// http://localhost/my/profile-view
	@GetMapping("/profile-view")
	public String profileView(Model model) {
		model.addAttribute("viewName", "my/profile");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/my/profile-edit-view
	@GetMapping("/profile-edit-view")
	public String profileEditView(Model model) {
		model.addAttribute("viewName", "my/editProfile");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/my/order-history-view
	@GetMapping("/order-history-view")
	public String orderHistoryView(Model model) {
		model.addAttribute("viewName", "my/orderHistory");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/my/cart-view
	@GetMapping("/cart-view")
	public String cartView(Model model) {
		model.addAttribute("viewName", "my/cart");
		model.addAttribute("viewNameR", "include/rightSide");
		return "template/layout";
	}
	
	
}
