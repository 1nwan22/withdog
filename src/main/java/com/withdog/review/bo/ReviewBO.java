package com.withdog.review.bo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.withdog.account.bo.AccountBO;
import com.withdog.account.entity.AccountEntity;
import com.withdog.inquiry.dto.InquiryDTO;
import com.withdog.inquiry.entity.InquiryEntity;
import com.withdog.review.dto.ReviewDTO;
import com.withdog.review.entity.ReviewEntity;
import com.withdog.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewBO {

	private final ReviewRepository reviewRepository;
	private final AccountBO accountBO;
	
	public void addReview(int accountId, int productId, int point, String content) {
		
		ReviewEntity review = reviewRepository.save(
				ReviewEntity.builder()
				.accountId(accountId)
				.productId(productId)
				.point(point)
				.content(content)
				.build());
		
		log.info("$$$$$$$$$$$$$$$ review = {}", review);
		
	}
	
	public List<ReviewDTO> getReviewListByProductId(int productId) {
		List<ReviewEntity> reviewEntityList = reviewRepository.findByProductId(productId);
		List<ReviewDTO> reviewList = new ArrayList<>(reviewEntityList.size());
				
		for (ReviewEntity reviewEntity : reviewEntityList)	 {
			ReviewDTO review = new ReviewDTO();
			
			review.setId(reviewEntity.getId());
			AccountEntity account = accountBO.getAccountEntityById(reviewEntity.getAccountId());
			review.setUserId(account.getUserId());
			review.setProductId(reviewEntity.getProductId());
			review.setPoint(reviewEntity.getPoint());
			review.setContent(reviewEntity.getContent());
			String createdAt = reviewEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String updatedAt = reviewEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			review.setCreatedAt(createdAt);
			review.setUpdatedAt(updatedAt);
			
			reviewList.add(review);
		}
		
		
		return reviewList;
	}
	
	public Page<ReviewDTO> generateReviewPage(Pageable pageable, int productId) {
		Page<ReviewEntity> reviewEntityPage = reviewRepository.findAllByProductId(pageable, productId);
		List<ReviewDTO> reviewList = new ArrayList<>();
		
		for (ReviewEntity reviewEntity : reviewEntityPage) {
			ReviewDTO review = new ReviewDTO();
			
			review.setId(reviewEntity.getId());
			AccountEntity account = accountBO.getAccountEntityById(reviewEntity.getAccountId());
			review.setUserId(account.getUserId());
			review.setProductId(reviewEntity.getProductId());
			review.setPoint(reviewEntity.getPoint());
			review.setContent(reviewEntity.getContent());
			String createdAt = reviewEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
			String updatedAt = reviewEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			review.setCreatedAt(createdAt);
			review.setUpdatedAt(updatedAt);
			
			//★★★★★ 마지막에 ViewList에 객체를 넣는다
			reviewList.add(review);
		}
		
		
		return new PageImpl<>(reviewList, pageable, reviewEntityPage.getTotalElements());
	}
	
	
}
