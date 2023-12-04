package com.withdog.payment.kakao.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class KakaoPayApproveResponse {

	private String aid; // 요청 고유 번호
    private String tid; // 결제 고유 번호
    private String cid; // 가맹점 코드
    private String sid; // 정기 결제용 ID, 정기 결제 CID로 단건 결제 요청 시 발급
    private String partner_order_id; // 가맹점 주문번호
    private String partner_user_id; // 가맹점 회원 id
    private String payment_method_type; // 결제 수단, CARD 또는 MONEY 중 하나
    private Amount amount; // 결제 금액 정보
    private CardInfo card_info; // 결제 금액 정보
    private String item_name; // 상품명
    private String item_code; // 상품 코드
    private Integer quantity; // 상품 수량
    private Date created_at; // 결제 요청 시간
    private Date approved_at; // 결제 승인 시간
    private String payload; // 결제 승인 요청에 대해 저장 값, 요청 시 전달 내용
    
    
    @ToString
    @Setter
    @Getter
    public class Amount {

    	private Integer total; // 전체 결제 금액
   	    private Integer tax_free; // 비과세 금액
   	    private Integer vat; // 부가세 금액
   	    private Integer point; // 사용한 포인트 금액
   	    private Integer discount; // 할인 금액
   	    private Integer green_deposit; // 컵 보증금
    }
    
    
    @ToString
    @Setter
    @Getter
    public class CardInfo {
    	
    	private String purchase_corp;  // 매입 카드사 한글명
    	private String purchase_corp_code;  // 매입 카드사 코드
    	private String issuer_corp;  // 카드 발급사 한글명
    	private String issuer_corp_code;  // 카드 발급사 코드
    	private String kakaopay_purchase_corp;  // 카카오페이 매입사명
    	private String kakaopay_purchase_corp_code;  // 카카오페이 매입사 코드
    	private String kakaopay_issuer_corp;  // 카카오페이 발급사명
    	private String kakaopay_issuer_corp_code;  // 카카오페이 발급사 코드
    	private String bin;  // 카드 BIN
    	private String card_type;  // 카드 타입
    	private String install_month;  // 할부 개월 수
    	private String approved_id;  // 카드사 승인번호
    	private String card_mid;  // 카드사 가맹점 번호
    	private String interest_free_install;  // 무이자할부 여부(Y/N)
    	private String card_item_code;  // 카드 상품 코드

    }
     
}
