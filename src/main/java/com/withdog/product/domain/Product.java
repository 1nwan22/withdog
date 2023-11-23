package com.withdog.product.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Product {

	private int id;
	
	private String name;
	
	private String brand;
	
	private int price;
	
	private int costPrice;
	
	private int stock;
	
	private String content;
	
	private String status;
	
	private Date createdAt;
	
	private Date updatedAt;
}
