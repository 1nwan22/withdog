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
	
	public KakaoPayReadyResponse kakaoPayReady() {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", CID);
        params.add("partner_order_id", "가맹점 주문 번호");
        params.add("partner_user_id", "가맹점 회원 ID");
        params.add("item_name", "상품명");
        params.add("quantity", "주문 수량");
        params.add("total_amount", "총 금액");
        params.add("tax_free_amount", "상품 비과세 금액");
        params.add("vat_amount", "부가세");
        params.add("approval_url", "http://localhost/payment/kakao/success");
        params.add("cancel_url", "http://localhost/payment/kakao/cancel");
        params.add("fail_url", "http://localhost/payment/kakao/fail");
        params.add("install_month", "카드 할부개월");
        
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
	
	public KakaoPayApproveResponse kakaoPayApprove(String pgToken) {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", CID);
        params.add("tid", kakaoPayReadyResponse.getTid());
        params.add("partner_order_id", "가맹점 주문 번호");
        params.add("partner_user_id", "가맹점 회원 ID");
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
