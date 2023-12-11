package com.withdog.inquiry;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.inquiry.bo.InquiryBO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inquiry")
@RestController
public class InquiryRestController {
	
	private final InquiryBO inquiryBO;

	@PostMapping("/add")
	public Map<String, Object> addInquiry(HttpSession session,
			@RequestParam int productId,
			@RequestParam int inquiryCategory,
			@RequestParam String inquirySubject,
			@RequestParam String inquiryContent,
			@RequestParam int inquirySecret) {
		
		Map<String, Object> result = new HashMap<>();
		Integer accountId = (Integer) session.getAttribute("accountId");
		
		if (ObjectUtils.isEmpty(accountId)) {
			result.put("code", 500);
			result.put("errorMessage", "로그인 후 문의하세요");
			return result;
		}
		log.info("$$$$$$$$$$$ productId = {}", productId);
		log.info("$$$$$$$$$$$ inquiryCategory = {}", inquiryCategory);
		log.info("$$$$$$$$$$$ inquirySubject = {}", inquirySubject);
		log.info("$$$$$$$$$$$ inquiryContent = {}", inquiryContent);
		log.info("$$$$$$$$$$$ inquirySecret = {}", inquirySecret);
		
		inquiryBO.addInquiry(accountId, productId, inquiryCategory, inquirySubject, inquiryContent, inquirySecret);
		
		
		
		
		return result;
	}
}
