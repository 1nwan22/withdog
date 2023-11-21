package com.withdog.product.bo;

import org.springframework.stereotype.Service;

import com.withdog.product.entity.ProductEntity;
import com.withdog.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductBO {

	private final ProductRepository productRepository;
	
	public Integer addProduct(String name, String brand, int price, int costPrice, int stock, String content, String status) {
		ProductEntity product = productRepository.save(
				ProductEntity.builder()
				.name(name)
				.brand(brand)
				.price(costPrice)
				.costPrice(costPrice)
				.stock(stock)
				.content(content)
				.status(status)
				.build());
		
		return product == null ? null : product.getId();
				
	}
}
