package com.withdog.product.bo;

import org.springframework.stereotype.Service;

import com.withdog.product.entity.ProductImageEntity;
import com.withdog.product.repository.ProductImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductImageBO {

	private final ProductImageRepository productImageRepository;
	
	public Integer addProductImage(int productId, String imagePath) {
		ProductImageEntity productImage = productImageRepository.save(
				ProductImageEntity.builder()
				.productId(productId)
				.ImagePath(imagePath)
				.build());
				
		return productImage == null ? null : productImage.getId();
	}
}
