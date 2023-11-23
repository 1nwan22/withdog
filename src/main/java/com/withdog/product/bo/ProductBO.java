package com.withdog.product.bo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.withdog.product.domain.Product;
import com.withdog.product.entity.ProductEntity;
import com.withdog.product.mapper.ProductMapper;
import com.withdog.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductBO {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	public Product getProductById(int id) {
		return productMapper.selectProductById(id);
	}
	
	@Transactional
	public Page<ProductEntity> getProductList(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	public Integer addProduct(String name, String brand, int price, int costPrice, int stock, String content, String status) {
		ProductEntity product = productRepository.save(
				ProductEntity.builder()
				.name(name)
				.brand(brand)
				.price(price)
				.costPrice(costPrice)
				.stock(stock)
				.content(content)
				.status(status)
				.build());
		
		return product == null ? null : product.getId();
				
	}
	
	public List<ProductEntity> getProduct(String key, Object value) {
		if (key.equals("name")) {
			return productRepository.findByName((String) value);
		} else if (key.equals("brand")) {
			return productRepository.findByBrand((String) value);
		} else if (key.equals("price")) {
			return productRepository.findByPrice((int) value);
		} else if (key.equals("costPrice")) {
			return productRepository.findByCostPrice((int) value);
		} else if (key.equals("stock")) {
			return productRepository.findByStock((int) value);
		}
		return null;
	}
}
