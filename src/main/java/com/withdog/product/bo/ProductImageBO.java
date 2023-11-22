package com.withdog.product.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.product.entity.ProductImageEntity;
import com.withdog.product.repository.ProductImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductImageBO {

	private final ProductImageRepository productImageRepository;
	
	public Integer addProductImage(int productId, List<MultipartFile> fileList) {
		String imagePath = null;
		
		ProductImageEntity productImage = productImageRepository.save(
				ProductImageEntity.builder()
				.productId(productId)
				.ImagePath(imagePath)
				.build());
				
		return productImage == null ? null : productImage.getId();
	}
}
