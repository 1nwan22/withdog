package com.withdog.admin;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	// http://localhost/admin/dash-board
	@GetMapping("/dash-board")
	public String dashBoard(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null || email.equals("pepper@pepper.com") == false) {
			return "redirect:/account/sign-in-view";
		}
		model.addAttribute("viewName", "/admin/dashBoard");
		return "template/layout";
	}
	
	// http://localhost/admin/product-manager
	@GetMapping("/product-manager")
	public String productManager(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null || email.equals("pepper@pepper.com") == false) {
			return "redirect:/account/sign-in-view";
		}
		model.addAttribute("viewName", "/admin/productManager");
		return "template/layout";
	}
	
	// http://localhost/admin/account-manager
	@GetMapping("/account-manager")
	public String accountManager(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null || email.equals("pepper@pepper.com") == false) {
			return "redirect:/account/sign-in-view";
		}
		model.addAttribute("viewName", "/admin/accountManager");
		return "template/layout";
	}
	
	// http://localhost/admin/order-manager
	@GetMapping("/order-manager")
	public String orderManager(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null || email.equals("pepper@pepper.com") == false) {
			return "redirect:/account/sign-in-view";
		}
		model.addAttribute("viewName", "/admin/orderManager");
		return "template/layout";
	}
}
