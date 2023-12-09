package com.withdog.order.domain;

import com.withdog.order.entity.OrderEntity;
import com.withdog.product.domain.ProductDTO;

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
public class OrderDTO {

	private OrderEntity order;
	private ProductDTO product;
	private int count;
	
}
