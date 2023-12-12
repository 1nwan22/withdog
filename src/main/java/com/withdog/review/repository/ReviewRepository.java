package com.withdog.review.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.withdog.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository <ReviewEntity, Integer> {

	public List<ReviewEntity> findByProductId(int productId);
	
	public Page<ReviewEntity> findAllByProductId(Pageable pageable, int productId);
}
