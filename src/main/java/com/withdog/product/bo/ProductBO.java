package com.withdog.product.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	
	public Page<ProductEntity> getProductList(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Transactional
	public Page<ProductView> generateProductViewPage() {
		List<ProductView> productViewList = new ArrayList<>(); // []
		List<ProductEntity> productList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		for (ProductEntity product : productList) {
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
			productViewList.add(productView);
		}
		// 원래 이 메소드는 정의해놓으면 Page를 자동반환 가능하다 지금은 Test느낌으로
		//List<User> userList = userRepotiroy.findAllByName(name);
		// 요청으로 들어온 page와 한 page당 원하는 데이터의 갯수
		PageRequest pageRequest = PageRequest.ofSize(12);
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), productViewList.size());
		Page<ProductView> productViewPage = new PageImpl<>(productViewList.subList(start, end), pageRequest, productViewList.size());

		return productViewPage;
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
