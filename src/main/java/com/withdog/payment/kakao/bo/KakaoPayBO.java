package com.withdog.payment.kakao.bo;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.withdog.payment.kakao.dto.KakaoPayApproveResponse;
import com.withdog.payment.kakao.dto.KakaoPayReadyResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class KakaoPayBO {
	
	private static final String CID = "TC0ONETIME"; // 가맹점 번호
	private static final String ADMINKEY = "7901aef37758282846605303baf8758c"; // 어드민 키
	private final WebClient webClient;
	private final KakaoPayReadyResponse kakaoPayReadyResponse;
	
	public KakaoPayReadyResponse kakaoPayReady(
			int orderId, int accountId,
			String productName, int count, int totalPrice) {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", CID);
        params.add("partner_order_id", String.valueOf(orderId));
        params.add("partner_user_id", String.valueOf(accountId));
        params.add("item_name", productName);
        params.add("quantity", String.valueOf(count));
        params.add("total_amount", String.valueOf(totalPrice));
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost/payment/kakao/success");
        params.add("cancel_url", "http://localhost/payment/kakao/cancel");
        params.add("fail_url", "http://localhost/payment/kakao/fail");
        
        KakaoPayReadyResponse KakaoPayReadyResponse = webClient.post()
				.uri("https://kapi.kakao.com/v1/payment/ready")
				.header("Authorization", "KakaoAK " + ADMINKEY)
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.body(BodyInserters.fromFormData(params))
				.retrieve()
				.bodyToMono(KakaoPayReadyResponse.class)
				.block();
		log.error("$$$$$$$$$$$$$$$ KakaoPayReadyResponse = {}", KakaoPayReadyResponse);
		
		return KakaoPayReadyResponse;
	}
	
	public KakaoPayApproveResponse kakaoPayApprove(String pgToken, int orderId, int accountId) {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", CID);
        params.add("tid", kakaoPayReadyResponse.getTid());
        params.add("partner_order_id", String.valueOf(orderId));
        params.add("partner_user_id", String.valueOf(accountId));
        params.add("pg_token", pgToken);
        
        KakaoPayApproveResponse KakaoPayApproveResponse = webClient.post()
				.uri("https://kapi.kakao.com/v1/payment/ready")
				.header("Authorization", "KakaoAK " + ADMINKEY)
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.body(BodyInserters.fromFormData(params))
				.retrieve()
				.bodyToMono(KakaoPayApproveResponse.class)
				.block();
		log.error("$$$$$$$$$$$$$$$ KakaoPayApproveResponse = {}", KakaoPayApproveResponse);
		
		return KakaoPayApproveResponse;
	}
	
	

}
