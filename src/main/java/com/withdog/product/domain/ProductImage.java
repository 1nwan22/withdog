package com.withdog.product.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ProductImage {

	private int id;

	private int productId;

	private String ImagePath;

	private Date createdAt;
}
