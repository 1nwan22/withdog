package com.withdog.admin;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.product.bo.ProductBO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

	private final ProductBO productBO;
	
	// http://localhost/admin/dash-board
	@GetMapping("/dash-board")
	public String dashBoard(Model model, HttpSession session) {
		
		model.addAttribute("viewName", "/admin/dashBoard");
		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/admin/product-manager
	@RequestMapping("/product-manager")
	public String productManager(Model model, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("products", productBO.getProductList(pageable));
		model.addAttribute("viewName", "/admin/productManager");
		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/admin/productManagerRSide");
		return "template/layout";
	}
	
	// http://localhost/admin/account-manager
	@GetMapping("/account-manager")
	public String accountManager(Model model, HttpSession session) {
	
		model.addAttribute("viewName", "/admin/accountManager");
		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/admin/order-manager
	@GetMapping("/order-manager")
	public String orderManager(Model model, HttpSession session) {
	
		model.addAttribute("viewName", "/admin/orderManager");
		return "template/layout";
	}
}
