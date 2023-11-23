package com.withdog.product.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.common.FileManagerService;
import com.withdog.product.entity.ProductImageEntity;
import com.withdog.product.repository.ProductImageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductImageBO {

	private final ProductImageRepository productImageRepository;
	
	private final FileManagerService fms;
	
	public Integer addProductImage(int productId, List<MultipartFile> images) {
		String imagePath = null;
		
		if (!ObjectUtils.isEmpty(images)) {
			for (MultipartFile image : images) {
				imagePath = fms.saveProductFile(productId, image);
			}
		}
		
		ProductImageEntity productImage = productImageRepository.save(
				ProductImageEntity.builder()
				.productId(productId)
				.ImagePath(imagePath)
				.build());
				
		return productImage == null ? null : productImage.getId();
	}
}
