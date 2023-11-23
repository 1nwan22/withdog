package com.withdog.product.mapper;

import org.springframework.stereotype.Repository;

import com.withdog.product.domain.Product;

@Repository
public interface ProductMapper {

	public Product selectProductById(int id);
}
