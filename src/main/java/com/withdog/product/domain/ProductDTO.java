package com.withdog.product.domain;

import java.util.List;

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
public class ProductDTO {

	private int id;
	
	private String name;
	
	private String brand;
	
	private int price;
	
	private int costPrice;
	
	private int stock;
	
	private String content;
	
	private String status;
	
	private List<ProductImage> productImageList;
	
}
