package com.withdog.admin;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.common.Paging;
import com.withdog.product.bo.ProductBO;
import com.withdog.product.entity.ProductEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

	private final ProductBO productBO;
	private final Paging paging;
	
	// http://localhost/admin/dash-board
	@GetMapping("/dash-board")
	public String dashBoard(Model model, HttpSession session) {
		model.addAttribute("viewName", "/admin/dashBoard");
		model.addAttribute("viewNameL", "/admin/leftSideAdmin");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/admin/product-manager
	@RequestMapping("/product-manager")
	public String productManager(Model model, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("$$$$$$ info pageable = {}", pageable);
		Page<ProductEntity> productList = productBO.getProductList(pageable);
		int currentPage = productList.getPageable().getPageNumber();
		int totalPages = productList.getTotalPages();
		model.addAttribute("minBundlePage", paging.getMinBundlePage(currentPage));
		model.addAttribute("maxBundlePage", paging.getMaxBundlePage(currentPage, totalPages));
		model.addAttribute("productList", productList);
		model.addAttribute("viewName", "/admin/productManager");
		model.addAttribute("viewNameR", "/admin/rightSideProductManager");
		return "template/layout";
	}
	
	// http://localhost/admin/account-manager
	@GetMapping("/account-manager")
	public String accountManager(Model model, HttpSession session) {
	
		model.addAttribute("viewName", "/admin/accountManager");
		model.addAttribute("viewNameL", "/admin/leftSideAdmin");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
	
	// http://localhost/admin/order-manager
	@GetMapping("/order-manager")
	public String orderManager(Model model, HttpSession session) {
	
		model.addAttribute("viewName", "/admin/orderManager");
		model.addAttribute("viewNameL", "/admin/leftSideAdmin");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
}
