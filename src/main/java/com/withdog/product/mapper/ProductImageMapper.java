package com.withdog.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.withdog.product.domain.ProductImage;

@Repository
public interface ProductImageMapper {

	public void insertProductImage(
			@Param("productId") int productId,
			@Param("imagePathList") List<String> imagePathList);
	
	public ProductImage selectImageByProductId(int productId);
	
	public List<ProductImage> selectImageListByProductId(int productId);
}
