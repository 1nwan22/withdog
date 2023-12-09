package com.withdog.product.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.product.domain.ProductDTO;
import com.withdog.product.domain.ProductView;
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
		productDTO.setId(id);
		productDTO.setName(productEntity.getName());
		productDTO.setBrand(productEntity.getBrand());
		productDTO.setPrice(productEntity.getPrice());
		productDTO.setCostPrice(productEntity.getCostPrice());
		productDTO.setStock(productEntity.getStock());
		productDTO.setContent(productEntity.getContent());
		productDTO.setStatus(productEntity.getStatus());
		productDTO.setProductImagePath(productImageBO.getImageByProductId(id).getImagePath());
		productDTO.setProductImageList(productImageBO.getImageListByProductId(id));
		
		return productDTO;
		
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
	
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
	
	public Page<ProductEntity> getProductList(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Transactional
	public Page<ProductView> generateProductViewPage(Pageable pageable) {
		
		Page<ProductEntity> productPage = productRepository.findAll(pageable);
		List<ProductView> productList = new ArrayList<>();
		
		for (ProductEntity product : productPage) {
			ProductView productView = new ProductView();
			// 상품
			productView.setProduct(product);
			
			// 상품 대표 이미지
			productView.setProductImage(productImageBO.getImageByProductId(product.getId()));
			
			// 상품 이미지들
			productView.setProductImageList(productImageBO.getImageListByProductId(product.getId()));
			
			// 리뷰들
			
			
			// 문의들
			
			//★★★★★ 마지막에 ViewList에 객체를 넣는다
			productList.add(productView);
		}
		return  new PageImpl<>(productList, pageable, productPage.getTotalElements());
	}
	
	@Transactional
	public ProductView generateProductView(int productId) {
		
		ProductView productView = new ProductView();
		ProductEntity product = productRepository.findById(productId).orElse(null);
		// 상품
		productView.setProduct(product);
			
		// 상품 대표 이미지
		productView.setProductImage(productImageBO.getImageByProductId(product.getId()));
			
		// 상품 이미지들
		productView.setProductImageList(productImageBO.getImageListByProductId(product.getId()));
			
		// 리뷰들
		
			
		// 문의들
			
		return  productView;
	}
	

	

}
