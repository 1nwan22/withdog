package com.withdog.product.bo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.product.domain.ProductDTO;
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
	
	public ProductDTO getProductDTOById(int id) {
		ProductEntity productEntity = productRepository.findById(id).orElse(null);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productEntity.getId());
		productDTO.setName(productEntity.getName());
		productDTO.setBrand(productEntity.getBrand());
		productDTO.setPrice(productEntity.getPrice());
		productDTO.setCostPrice(productEntity.getCostPrice());
		productDTO.setStock(productEntity.getStock());
		productDTO.setContent(productEntity.getContent());
		productDTO.setStatus(productEntity.getStatus());
		productDTO.setProductImagePath(productImageBO.getImageByProductId(productEntity.getId()).getImagePath());
		productDTO.setProductImageList(productImageBO.getImageListByProductId(productEntity.getId()));
		
		return productDTO;
		
	}
	
	public Page<ProductEntity> generateProductPage(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	
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
		
		// 이미지 , 리뷰, 문의 삭제 추가
	}
	
	public Page<ProductEntity> getProductList(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	
	

	

}
