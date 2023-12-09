<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="p-2">
	<div>
		<h1>주문결제</h1>
	</div>
	<div class="d-flex">
		<!-- 주문 정보 -->
		<div class="order-info-box">
			<!-- 배송지 정보 박스 -->
			<div class="shipping-info-box">
				배송지정보 modal로 띄우기
			</div>
			
			<!-- 결제수단 박스 -->
			<div class="payment-method-box"> 
				결제수단
			</div>
		</div>
		<!-- 결제하기 박스 -->
		<div class="payment-info-box">
			주문상품 개수
			<!-- 주문 상품 정보 -->
			<div class="product-info-box">
				브랜드
				썸네일
				상품명
				상품가격
				상품수량
			</div>
			<div class="payment-box">
				총 상품금액
				할인금액
				배송비
				총 결제금액
			<button type="button" id="kakaoPayBtn" class="btn btn-warning">카카오페이</button>
			<button type="button" id="testBtn" class="btn btn-warning">테스트 결제</button>
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>

<script>
	$(document).ready(function () {
		$("#kakaoPayBtn").on("click", function() {
			 
			$.ajax({
				type:"POST"
				, url:"/payment/kakao/ready"
				, data:{}
			 
			 	, success:function(data) {
			 		alert("결제완료");
			 	}
			 	, error:function(request, status, error) {
			 		alert("결제실패");
			 	}
			 });
		 });
		
		/* function requestPayment() {
		    PortOne.requestPayment({
		      // 가맹점 storeId로 변경해주세요.
		      storeId: 'store-4ff4af41-85e3-4559-8eb8-0d08a2c6ceec',
		      paymentId: 'paymentId_{now()}',
		      orderName: '나이키 와플 트레이너 2 SD',
		      totalAmount: 1000,
		      currency: 'CURRENCY_KRW',
		      pgProvider: 'PG_PROVIDER_TOSSPAYMENTS',
		      payMethod: "CARD"
		    });
		  } */
	});
</script>