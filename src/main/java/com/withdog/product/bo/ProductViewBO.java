package com.withdog.product.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.withdog.inquiry.bo.InquiryBO;
import com.withdog.product.domain.ProductDTO;
import com.withdog.product.domain.ProductView;
import com.withdog.product.entity.ProductEntity;
import com.withdog.review.bo.ReviewBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductViewBO {

	private final ProductBO productBO;
	private final ProductImageBO productImageBO;
	private final ReviewBO reviewBO;
	private final InquiryBO inquiryBO;
	
	
	@Transactional
	public Page<ProductView> generateProductViewPage(Pageable pageable) {
		
		Page<ProductEntity> productPage = productBO.generateProductPage(pageable);
		List<ProductView> productList = new ArrayList<>();
		log.info("$$$$$$$$$$$$$$$$$$$$$ productPage 갯수", productPage.getNumberOfElements());
		for (ProductEntity productEntity : productPage) {
			ProductView productView = new ProductView();
			// 상품
			ProductDTO productDTO = productBO.getProductDTOById(productEntity.getId());
			productView.setProduct(productDTO);
			
			// 상품 대표 이미지
			productView.setProductImage(productImageBO.getImageByProductId(productEntity.getId()));
			
			//★★★★★ 마지막에 ViewList에 객체를 넣는다
			productList.add(productView);
		}
		return  new PageImpl<>(productList, pageable, productPage.getTotalElements());
	}
	
	@Transactional
	public ProductView getProductView(Pageable pageable, int productId) {
		
		ProductView productView = new ProductView();
		ProductDTO product = productBO.getProductDTOById(productId);
		// 상품
		productView.setProduct(product);
			
		// 상품 대표 이미지
		productView.setProductImage(productImageBO.getImageByProductId(product.getId()));
			
		// 상품 이미지들
		productView.setProductImageList(productImageBO.getImageListByProductId(product.getId()));
			
		// 리뷰들
		productView.setReviewList(reviewBO.getReviewListByProductId(product.getId()));
		
		productView.setReviewPage(reviewBO.generateReviewPage(pageable, productId));
			
		// 문의들
		productView.setInquiryList(inquiryBO.getInquiryListByProductId(product.getId()));
		
		productView.setInquiryPage(inquiryBO.generateInquiryPage(pageable, product.getId()));
		log.info("$$$$$$$$$$$$$$$$$$$ total page{}", inquiryBO.generateInquiryPage(pageable, product.getId()).getTotalPages());
		
			
		return  productView;
	}
}
