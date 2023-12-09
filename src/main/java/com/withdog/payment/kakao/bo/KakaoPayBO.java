package com.withdog.payment.kakao.bo;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.withdog.order.bo.OrderViewBO;
import com.withdog.order.domain.OrderView;
import com.withdog.payment.kakao.dto.KakaoPayApproveResponse;
import com.withdog.payment.kakao.dto.KakaoPayReadyRequest;
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
	private final OrderViewBO orderViewBO;
	
	public KakaoPayReadyRequest kakaoPayReadyRequest(int orderId, int accountId) {
		KakaoPayReadyRequest kakaoPayReadyRequest = new KakaoPayReadyRequest();
		OrderView orderView = orderViewBO.getOrderView(orderId);
		String productName = orderView.getOrderedProductList().get(0).getName();
		String count = String.valueOf(orderView.getOrderedProductList().get(0).getCount());
		String totalPrice = String.valueOf(orderView.getOrder().getTotalPrice());
		kakaoPayReadyRequest.setOrderId(String.valueOf(orderId));
		kakaoPayReadyRequest.setAccountId(String.valueOf(accountId));
		kakaoPayReadyRequest.setProductName(productName);
		kakaoPayReadyRequest.setCount(count);
		kakaoPayReadyRequest.setTotalPrice(totalPrice);
		
		return kakaoPayReadyRequest;
	}
	
public KakaoPayReadyResponse kakaoPayReady(KakaoPayReadyRequest request) {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", CID);
        params.add("partner_order_id", request.getOrderId());
        params.add("partner_user_id", request.getAccountId());
        params.add("item_name", request.getProductName());
        params.add("quantity", request.getCount());
        params.add("total_amount", request.getTotalPrice());
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost/payment/kakao/success");
        params.add("cancel_url", "http://localhost/payment/kakao/cancel");
        params.add("fail_url", "http://localhost/payment/kakao/fail");
        
        KakaoPayReadyResponse kakaoPayReadyResponse = webClient.post()
				.uri("https://kapi.kakao.com/v1/payment/ready")
				.header("Authorization", "KakaoAK " + ADMINKEY)
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.body(BodyInserters.fromFormData(params))
				.retrieve()
				.bodyToMono(KakaoPayReadyResponse.class)
				.block();
		log.info("$$$$$$$$$$$$$$$ KakaoPayReadyResponse = {}", kakaoPayReadyResponse);
		
		return kakaoPayReadyResponse;
	}
	
	public KakaoPayApproveResponse kakaoPayApprove(String pgToken, String tid, int orderId, int accountId) {
		log.error("$$$$$$$$$$$$$$$$: kakaoPayReadyResponse.getTid() = {}", kakaoPayReadyResponse.getTid());
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", CID);
        params.add("tid", tid);
        params.add("partner_order_id", String.valueOf(orderId));
        params.add("partner_user_id", String.valueOf(accountId));
        params.add("pg_token", pgToken);
        
        KakaoPayApproveResponse kakaoPayApproveResponse = webClient.post()
				.uri("https://kapi.kakao.com/v1/payment/approve")
				.header("Authorization", "KakaoAK " + ADMINKEY)
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.body(BodyInserters.fromFormData(params))
				.retrieve()
				.bodyToMono(KakaoPayApproveResponse.class)
				.block();
		log.info("$$$$$$$$$$$$$$$ kakaoPayApproveResponse = {}", kakaoPayApproveResponse);
		
		return kakaoPayApproveResponse;
	}
	
	

}
