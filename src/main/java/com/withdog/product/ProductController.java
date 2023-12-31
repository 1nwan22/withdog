package com.withdog.product;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.withdog.common.Paging;
import com.withdog.product.bo.ProductViewBO;
import com.withdog.product.domain.ProductView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductController {
	
	private final ProductViewBO productViewBO;
	private final Paging paging;

	// http://localhost/product/list-view
	@GetMapping("/list-view")
	public String productListView(
			Model model,
			@PageableDefault(size = 12, sort = "id" ,direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("$$$$$$$$$$$ info pageable = {} $$$$$$$$$$$", pageable);
		
		Page<ProductView> productList = productViewBO.generateProductViewPage(pageable);
		log.info("$$$$$$$$$$$ info productList = {} $$$$$$$$$$$", productList);
		
		int currentPage = productList.getPageable().getPageNumber();
		log.info("$$$$$$$$$$$ info currentPage = {} $$$$$$$$$$$", currentPage);
		
		int totalPages = productList.getTotalPages();
		log.info("$$$$$$$$$$$ info totalPages = {} $$$$$$$$$$$", totalPages);
		
		model.addAttribute("minBundlePage", paging.getMinBundlePage(currentPage));
		model.addAttribute("maxBundlePage", paging.getMaxBundlePage(currentPage, totalPages));
		model.addAttribute("productList", productList);
		model.addAttribute("viewName", "product/productList");
		model.addAttribute("viewNameR", "product/rightSideProduct");
		
		return "template/layout";
	}
	
	@GetMapping("/{productId}")
	public String productView(Model model, HttpSession session,
			@PathVariable int productId,
			@RequestParam(name ="productContentName", required = false) String productContentName,
			@RequestParam(name="page", required=false) Integer page,
			@PageableDefault(size = 10, sort = "id" ,direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("$$$$$$ info productId = {}", productId);
		String userId = (String) session.getAttribute("userId");
		ProductView product = productViewBO.getProductView(pageable, productId);
		
		int currentPage = product.getInquiryPage().getNumber();
		log.info("$$$$$$$$$$$ info currentPage = {} $$$$$$$$$$$", currentPage);
		
		int totalPages = product.getInquiryPage().getTotalPages();
		log.info("$$$$$$$$$$$ info totalPages = {} $$$$$$$$$$$", totalPages);
		
		model.addAttribute("minBundlePage", paging.getMinBundlePage(currentPage));
		model.addAttribute("maxBundlePage", paging.getMaxBundlePage(currentPage, totalPages));
		
		if(ObjectUtils.isEmpty(productContentName)) {
			productContentName = "product/inquiry.jsp";
		}
		log.info("$$$$$$$$$$$ productContentName = {} $$$$$$$$$$$", productContentName);
		
		model.addAttribute("userId", userId);
		model.addAttribute("product", product);
		model.addAttribute("viewName", "product/product");
		model.addAttribute("viewNameR", "product/rightSideProduct");
		model.addAttribute("productContentName", productContentName);
		return "template/layout";
	}
	

}
