package com.withdog.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.product.bo.ProductBO;
import com.withdog.product.domain.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductController {
	
	private final ProductBO productBO;

	// http://localhost/product/list-view
	@GetMapping("/list-view")
	public String productListView(
			Model model,
			@PageableDefault(size = 12, sort = "id" ,direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("$$$$$$ info pageable = {}", pageable);
		model.addAttribute("productList", productBO.getProductList(pageable));
		model.addAttribute("viewName", "/product/productList");
		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
	
	@GetMapping("/{productId}")
	public String productView(Model model, 
			@PathVariable int productId) {
		
		Product product = productBO.getProductById(productId);
		model.addAttribute("product", product);
		model.addAttribute("viewName", "/product/product");
		model.addAttribute("viewNameL", "/include/leftSide");
		model.addAttribute("viewNameR", "/include/rightSide");
		return "template/layout";
	}
}
