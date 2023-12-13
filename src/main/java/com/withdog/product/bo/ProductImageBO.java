package com.withdog.product.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.common.FileManagerService;
import com.withdog.product.domain.ProductImage;
import com.withdog.product.mapper.ProductImageMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductImageBO {

	private final ProductImageMapper productImageMapper;
	
	private final FileManagerService fms;
	
	@Transactional
	public void addProductImage(int productId, List<MultipartFile> imageList) {
		String imagePath = null;
		List<String> imagePathList = new ArrayList<>(imageList.size());

		if (!ObjectUtils.isEmpty(imageList)) {
			for (MultipartFile image : imageList) {
				imagePath = fms.saveImageFile(productId, image);
				imagePathList.add(imagePath);
			}
		}
		productImageMapper.insertProductImage(productId, imagePathList);
	}
	
	public ProductImage getImageByProductId(int productId) {
		return productImageMapper.selectImageByProductId(productId);
	}
	
	public List<ProductImage> getImageListByProductId(int productId) {
		return productImageMapper.selectImageListByProductId(productId);
	}
}
