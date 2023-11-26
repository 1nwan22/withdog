package com.withdog.product.bo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.product.entity.ProductEntity;
import com.withdog.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductBO {

	private final ProductRepository productRepository;
	private final ProductImageBO productImageBO;
	
	public ProductEntity getProductById(int id) {
		return productRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Page<ProductEntity> getProductList(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Transactional
	public Integer addProduct(String name, String brand, int price, int costPrice, int stock, String content, String status, List<MultipartFile> imageList) {
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
		
		productImageBO.addProductImage(product.getId(), imageList);
		
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
	
	@Transactional
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
}
