package com.withdog.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.product.bo.ProductBO;
import com.withdog.product.bo.ProductImageBO;

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
			@RequestParam("content") int content,
			@RequestParam("status") String status,
			@RequestParam("fileList") List<MultipartFile> fileList) {
		
		Integer productId = (Integer) productBO.addProduct(name, brand, price, costPrice, stock, brand, status);
		productImageBO.addProductImage(productId, fileList);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping("/get-product")
	public Map<String, Object> getProduct() {
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "success");
		return result;
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
