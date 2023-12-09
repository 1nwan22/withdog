package com.withdog.payment.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class KakaoPayReadyRequest {

	private String orderId;
	private String accountId;
	private String productName;
	private String count;
	private String totalPrice;
}
