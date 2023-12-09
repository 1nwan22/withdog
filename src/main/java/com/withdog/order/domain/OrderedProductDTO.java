package com.withdog.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderedProductDTO {

	// 주문 번호
	private int orderId;
	
	// 주문한 상품 번호
	private int productId;
	
	// 주문한 상품 브랜드
	private String brand;
		
	// 주문한 상품 이름
	private String name;
	
	// 주문한 상품 가격
	private int price;
	
	// 주문한 상품 재고
	private int stock;
	
	// 주문한 상품 상태
	private String status;
	
	// 주문한 상품 이미지
	private String productImagePath;
	
	// 주문한 상품 수량
	private int count;
	
}
