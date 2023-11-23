package com.withdog.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.withdog.product.bo.ProductBO;
import com.withdog.product.bo.ProductImageBO;
import com.withdog.product.entity.ProductEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductRestController {
	
	private final ProductBO productBO;
	private final ProductImageBO productImageBO;

	@PostMapping("/add-product")
	public Map<String, Object> addProduct(
			@RequestParam("name") String name,
			@RequestParam("brand") String brand,
			@RequestParam("price") int price,
			@RequestParam("costPrice") int costPrice,
			@RequestParam("stock") int stock,
			@RequestParam("content") String content,
			@RequestParam("status") String status,
			@RequestPart("images") List<MultipartFile> images) {
		
		Integer productId = (Integer) productBO.addProduct(name, brand, price, costPrice, stock, brand, status);
		productImageBO.addProductImage(productId, images);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping("/get-product")
	public String getProduct(
			@RequestParam("targetProductKey") String key,
			@RequestParam("targetProductValue") Object value,
			RedirectAttributes redirectAttributes) {
		List<ProductEntity> productList = productBO.getProduct(key, value);
		redirectAttributes.addFlashAttribute("productList", productList);

		return "redirect:/admin/product-manager";
	}
	
	@PutMapping("/edit-product")
	public Map<String, Object> editProduct() {
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		return result;
	}
	
	@DeleteMapping("/delete-product")
	public Map<String, Object> deleteProduct() {
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		return result;
	}
}
