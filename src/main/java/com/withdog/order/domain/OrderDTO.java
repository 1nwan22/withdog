package com.withdog.order.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderDTO {

	// 주문번호
	private int id;
	
	// 보내는 사람
	private int accountId;
	
	// 받는 사람
	private String receiverName;
	
	// 배송지
	private String receiverAddress;
	
	// 받는 사람 핸드폰 번호
	private String receiverPhoneNumber;
	
	// 요청 사항
	private String request;
	
	// 결제 수단
	private String paymentMethodType;
	
	// 카드 종류 -> enum으로
	private String card;
	
	// 배송비
	private int shippingPrice;
	
	// 총 결제 금액
	private int totalPrice;
	
	// 배송상태
	private String status;
	
	// 결제 시각
	private Date payAt;
	
	// 출발 시각
	private Date departAt;
	
	// 도착 시각
	private Date arrivalAt;
	
}
