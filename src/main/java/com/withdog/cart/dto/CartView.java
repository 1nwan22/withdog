package com.withdog.cart.dto;

import com.withdog.product.domain.ProductImage;
import com.withdog.product.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CartView {

	private ProductEntity product;
	
	private ProductImage productImage;
	
	private int count;
}
