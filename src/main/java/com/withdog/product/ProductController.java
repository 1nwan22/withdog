package com.withdog.product;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product")
@Controller
public class ProductController {

	// http://localhost/product/add-product-view
	@GetMapping("/add-product-view")
	public String addProduct(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email.equals("pepper@pepper.com")) {
			model.addAttribute("viewName", "/product/addProduct");
			return "template/layout";
		}
		return "redirect:/account/sign-in-view";
	}
	
	// http://localhost/product/get-product-view
	@GetMapping("/get-product-view")
	public String getProduct(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email.equals("pepper@pepper.com")) {
			model.addAttribute("viewName", "/product/addProduct");
			return "template/layout";
		}
		return "redirect:/account/sign-in-view";
	}
	
	// http://localhost/product/update-product-view
	@GetMapping("/update-product-view")
	public String updateProduct(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email.equals("pepper@pepper.com")) {
			model.addAttribute("viewName", "/product/addProduct");
			return "template/layout";
		}
		return "redirect:/account/sign-in-view";
	}
	
	// http://localhost/product/delete-product-view
	@GetMapping("/delete-product-view")
	public String deleteProductt(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email.equals("pepper@pepper.com")) {
			model.addAttribute("viewName", "/product/addProduct");
			return "template/layout";
		}
		return "redirect:/account/sign-in-view";
	}
	
}
