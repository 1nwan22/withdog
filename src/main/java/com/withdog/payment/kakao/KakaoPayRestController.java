package com.withdog.payment.kakao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.payment.kakao.bo.KakaoPayBO;
import com.withdog.payment.kakao.dto.KakaoPayReadyRequest;
import com.withdog.payment.kakao.dto.KakaoPayReadyResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment/kakao")
@RestController
public class KakaoPayRestController {

	private final KakaoPayBO kakaoPayBO;
	
    @PostMapping("/ready")
    public Map<String, Object> kakaoPayReady(
    		HttpSession session,
    		@RequestParam int orderId) {

    	int accountId = (int) session.getAttribute("accountId");
    	session.setAttribute("orderId", orderId);
    	KakaoPayReadyRequest request = kakaoPayBO.kakaoPayReadyRequest(orderId, accountId);
    	KakaoPayReadyResponse response = kakaoPayBO.kakaoPayReady(request);
    	session.setAttribute("tid", response.getTid());
    	log.info("$$$$$$$$$$$$$$$$$$$$ KakaoPayReadyResponse", response);
    	Map<String, Object> result = new HashMap<>();
    	result.put("redirect", response.getNext_redirect_pc_url());
    	
    	return result;
    }
    
   



}
