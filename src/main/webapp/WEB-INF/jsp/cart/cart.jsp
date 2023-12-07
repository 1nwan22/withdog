<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="w-100">
	<div class="d-flex">
		<h1>장바구니</h1>
		<div>
			장바구니 > 주문결제 > 주문완료
		</div>
	</div>
	<!-- 장바구니 목록 -->
	<div class="d-flex w-100">
		<div class="col-8 bg-info">
			<c:forEach items="${cartList}" var="cart">
				<div>
					<div>
					전체선택 선택삭제
					</div>
					<div>
					<label>
						<input class="cart-radio" type="checkbox" name="cart" data-product-id="${cart.product.id}">선택
					</label>
					${cart.product.brand}
					<img src="${cart.productImage.imagePath}" alt="상품썸네일" width="150px">
					${cart.product.name}
					${cart.product.price}
					<div class="cartCount" data-product-count="${cart.count}">${cart.count}</div>
					
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- 결제 정보 구매하기 > order > checkout -->
		<div class="col-4">
			<div>
				상품수
				상품금액
				할인금액
				총결제금액
				<button type="button" id="orderBtn" class="btn btn-success">구매하기</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#orderBtn").on("click", function() {
			let checkedArr = [];
			$("input[name=cart]:checked").each(function(){
				checkedArr.push($(this).val());
			});
			console.log(checkedArr);
		});
	});
</script>