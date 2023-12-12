package com.withdog.inquiry.bo;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.withdog.inquiry.dto.InquiryDTO;
import com.withdog.inquiry.entity.InquiryEntity;
import com.withdog.inquiry.repository.InquiryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class InquiryBO {
	
	private final InquiryRepository inquiryRepository;
	
	public void addInquiry(
			int accountId,
			int productId,
			int category,
			String subject,
			String content,
			int secret) {
		
		InquiryEntity inquiry = inquiryRepository.save(
				InquiryEntity.builder()
				.accountId(accountId)
				.productId(productId)
				.category(category)
				.subject(subject)
				.content(content)
				.secret(secret)
				.status("처리중")
				.build());
		
		log.info("$$$$$$$$$$$$ inquiryId = {}", inquiry.getId());
		
	}
	
	public List<InquiryDTO> getInquiryListByProductId(int productId) {
		List<InquiryEntity> inquiryEntityList = inquiryRepository.findByProductId(productId);
		List<InquiryDTO> inquiryList = new ArrayList<>(inquiryEntityList.size());
		
		for (InquiryEntity inquiryEntity : inquiryEntityList) {
			InquiryDTO inquiry = new InquiryDTO();
			
			inquiry.setId(inquiryEntity.getId());
			inquiry.setAccountId(inquiryEntity.getAccountId());
			inquiry.setProductId(inquiryEntity.getProductId());
			String category = null;
			if (inquiryEntity.getCategory() == 1) {
				category = "상품";
			} else if (inquiryEntity.getCategory() == 2) {
				category = "배송";
			} else if (inquiryEntity.getCategory() == 3) {
				category = "취소";
			} else if (inquiryEntity.getCategory() == 4) {
				category = "반품";
			} else if (inquiryEntity.getCategory() == 5) {
				category = "교환";
			} else {
				category = "기타";
			}
			inquiry.setCategory(category);
			inquiry.setSubject(inquiryEntity.getSubject());
			inquiry.setContent(inquiryEntity.getContent());
			inquiry.setSecret(inquiryEntity.getSecret());
			inquiry.setStatus(inquiryEntity.getStatus());
			inquiry.setReply(inquiryEntity.getReply());
			String createdAt = inquiryEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			inquiry.setCreatedAt(createdAt);
			
			inquiryList.add(inquiry);
		}
		
		return inquiryList;
	}
	
	public Page<InquiryDTO> generateInquiryPage(Pageable pageable, int productId) {
		Page<InquiryEntity> inquiryPage = inquiryRepository.findAllByProductId(pageable, productId);
		List<InquiryEntity> inquiryEntityList = inquiryRepository.findByProductId(productId);
		List<InquiryDTO> inquiryList = new ArrayList<>(inquiryEntityList.size());
		
		for (InquiryEntity inquiryEntity : inquiryPage) {
			InquiryDTO inquiry = new InquiryDTO();
			
			inquiry.setId(inquiryEntity.getId());
			inquiry.setAccountId(inquiryEntity.getAccountId());
			inquiry.setProductId(inquiryEntity.getProductId());
			String category = null;
			if (inquiryEntity.getCategory() == 1) {
				category = "상품";
			} else if (inquiryEntity.getCategory() == 2) {
				category = "배송";
			} else if (inquiryEntity.getCategory() == 3) {
				category = "취소";
			} else if (inquiryEntity.getCategory() == 4) {
				category = "반품";
			} else if (inquiryEntity.getCategory() == 5) {
				category = "교환";
			} else {
				category = "기타";
			}
			inquiry.setCategory(category);
			inquiry.setSubject(inquiryEntity.getSubject());
			inquiry.setContent(inquiryEntity.getContent());
			inquiry.setSecret(inquiryEntity.getSecret());
			inquiry.setStatus(inquiryEntity.getStatus());
			inquiry.setReply(inquiryEntity.getReply());
			String createdAt = inquiryEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			inquiry.setCreatedAt(createdAt);
			
			//★★★★★ 마지막에 ViewList에 객체를 넣는다
			inquiryList.add(inquiry);
		}
		
		
		return new PageImpl<>(inquiryList, pageable, inquiryEntityList.size());
	}

}
