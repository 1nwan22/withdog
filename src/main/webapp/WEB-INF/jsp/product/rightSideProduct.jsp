<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="rightSideWrap" class="right-side-wrap p-3 w-100">
	<!-- 담은 상품들 -->
	<div>
		<div class="cart-box">
			<div class="d-felx justify-content-between">
				<div>상품명</div>
				<div>x</div>
			</div>
			<hr>
			<div class="d-flex justify-content-between">
				<div>- 버튼</div>
				<div>수량</div>
				<div>+ 버튼</div>
			</div>
			<div>
				상품가격
			</div>
		</div>
	</div>
	
	<hr>
	<!-- 버튼 박스 -->
	<div>
		총금액
	</div>
	<div>
		<a href="/cart-view" id="cartBtn" class="btn btn-light form-control">장바구니</a>
		<a href="/checkout-view" id="payBtn" class="btn btn-primary form-control">구매하기</a>
	</div>
</div>

<script>
	$(document).ready(function() {
		let currentPosition = parseInt($(".right-side-wrap").css("top"));
		$(window).scroll(function() {
			let position = $(window).scrollTop(); 
			$("#rightSideWrap").stop().animate({"top":position+currentPosition+"px"},500);
		}); 
	});
</script>