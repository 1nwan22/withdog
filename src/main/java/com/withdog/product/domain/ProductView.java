package com.withdog.product.domain;

import java.util.List;

import com.withdog.product.entity.ProductEntity;

import lombok.Data;

@Data
public class ProductView {

	private ProductEntity product;
	
	private ProductImage productImage;
	
	private List<ProductImage> productImageList;
	
	//TODO 리뷰 , 평점 , 문의	
}
