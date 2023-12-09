<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<c:forEach var="orderedProduct" items="${order.orderedProductList}">
				    <div class="product-info-box">
				        <div>${orderedProduct.brand}</div>
				        <img src="${orderedProduct.productImagePath}" width="150px">
				        <div>상품명: ${orderedProduct.name}</div>
				        <div>상품가격: ${orderedProduct.price}</div>
				        <div>상품수량: ${orderedProduct.count}</div>
				    </div>
				</c:forEach>
			<!-- 결제 정보 -->
			<div class="payment-box" data-order-id="${order.order.id}">
				총 상품금액
				할인금액
				배송비
					
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
			let orderId = $(".payment-box").data("order-id");
			console.log(orderId);
			$.ajax({
				type:"POST"
				, url:"/payment/kakao/ready"
				, data:{"orderId":orderId}
			 
			 	, success:function(data) {
			 		location.href= data.redirect
			 	}
			 	, error:function(request, status, error) {
			 		alert("결제실패");
			 	}
			 });
		 });
		

	});
</script>