package com.withdog.product.domain;

import java.util.List;

import org.springframework.data.domain.Page;

import com.withdog.inquiry.dto.InquiryDTO;
import com.withdog.review.dto.ReviewDTO;

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
public class ProductView {

	private ProductDTO product;
	
	private ProductImage productImage;
	
	private List<ProductImage> productImageList;
	
	private List<ReviewDTO> reviewList;
	
	private List<InquiryDTO> inquiryList;
	
	private Page<InquiryDTO> inquiryPage;
	
	//TODO 리뷰 , 평점 , 문의	
}
