package com.withdog.inquiry.bo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.withdog.inquiry.dto.InquiryDTO;
import com.withdog.inquiry.entity.InquiryEntity;
import com.withdog.inquiry.repository.InquiryRepository;
import com.withdog.product.domain.ProductView;
import com.withdog.product.entity.ProductEntity;

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
		
	}
	
	public Page<InquiryDTO> generateProductViewPage(Pageable pageable) {
		Page<InquiryEntity> inquiryEntityPage = inquiryRepository.findAll(pageable);
		List<InquiryDTO> inquiryList = new ArrayList<>(inquiryEntityPage.getNumberOfElements());
		log.info("$$$$$$$$$$$$$$$$$$$$$$$$$ inquiryPage 갯수", inquiryEntityPage.getNumberOfElements());
		
		for (InquiryEntity inquiryEntity : inquiryEntityPage) {
			InquiryDTO inquiry = new InquiryDTO();
			
			inquiry.setId(inquiryEntity.getId());
			inquiry.setAccountId(inquiryEntity.getAccountId());
			inquiry.setProductId(inquiryEntity.getProductId());
			inquiry.setCategory(inquiryEntity.getCategory());
			inquiry.setSubject(inquiryEntity.getSubject());
			inquiry.setContent(inquiryEntity.getContent());
			inquiry.setSecret(inquiryEntity.getSecret());
			inquiry.setStatus(inquiryEntity.getStatus());
			inquiry.setReply(inquiryEntity.getReply());
			inquiry.setCreatedAt(inquiryEntity.getCreatedAt());
			
			//★★★★★ 마지막에 ViewList에 객체를 넣는다
			inquiryList.add(inquiry);
		}
		
		return  new PageImpl<>(inquiryList, pageable, inquiryEntityPage.getTotalElements());
	}

}
