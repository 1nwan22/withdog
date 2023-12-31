package com.withdog.product.domain;

import java.time.LocalDateTime;

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
public class ProductImage {

	private int id;

	private int productId;

	private String imagePath;

	private LocalDateTime createdAt;
}
