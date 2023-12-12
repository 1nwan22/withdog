package com.withdog.inquiry.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.withdog.inquiry.entity.InquiryEntity;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Integer> {

	public List<InquiryEntity> findByProductId(int productId);
	
	public Page<InquiryEntity> findAllByProductId(Pageable pageable, int productId);
}
