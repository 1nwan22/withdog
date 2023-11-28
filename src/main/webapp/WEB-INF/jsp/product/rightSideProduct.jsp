<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="rightSideWrap">
	<div class="odrder-box">
		<h1>상품목록</h1>
		<div>상품명</div>
		<div>수량</div>
		<div>합계금액</div>
	</div>
	<div class="payment-box">
		<div class="payment-info">
			<div>결제금액</div>
			<div>할인금액</div>
			<div>총 금액</div>
		</div>
		<button type="button" id="payBtn" class="btn btn-info form-control">결제하기</button>
	</div>
</div>

<script>
$(document).ready(function() {
	
	let currentPosition = parseInt($("#rightSideWrap").css("top"));
	$(window).scroll(function() {
		let position = $(window).scrollTop(); 
		$("#rightSideWrap").stop().animate({"top":position+currentPosition+"px"},500);
	});
		
});
</script>