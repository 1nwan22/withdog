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
						<input class="cart-radio" type="checkbox" name="cart" 
						data-product-id="${cart.product.id}" 
						data-product-count="${cart.count}" 
						data-product-price="${cart.product.price}">
						선택
					</label>
					${cart.product.brand}
					<img src="${cart.productImage.imagePath}" alt="상품썸네일" width="150px">
					${cart.product.name}
					${cart.product.price}
					<div class="cartCount" >${cart.count}</div>
					
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- 결제 정보 구매하기 > order > checkout -->
		<div class="col-4">
			<div>
				상품수: <span class="total-quantity"></span>
				총 상품금액: <span class="total-amount"></span>
				할인금액: <span class="discount"></span>
				배송비: <span class="shipping-cost"></span>
				총결제금액 <span class="total-payment"></span>
				<button type="button" class="order-btn btn btn-success">구매하기</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		$("input[name=cart]").prop("checked", true);
		
		let checkedCartList = new Array();
		$("input[name=cart]:checked").each(function(){
			let data = new Object();
			data.productId = $(this).data("product-id");
			data.count = $(this).data("product-count");
			data.price = $(this).data("product-price")
			
			checkedCartList.push(data);
		});
		
		// 총 상품수, 상품금액, 총 결제금액 계산
		let totalQuantity = 0;
		let totalAmount = 0;
		checkedCartList.forEach(function(cart) {
			totalQuantity += cart.count;
			totalAmount += cart.count * cart.price;
		});
		
		// 할인금액 등 추가 계산 로직이 있다면 이곳에 추가
		let shippingCost = 3000;
		if (totalAmount > 40000) {
			shippingCost = 0;
		}
		// 총 상품수, 상품금액, 총 결제금액 화면에 표시
		$(".total-quantity").text(totalQuantity);
		$(".total-amount").text(totalAmount);
		$(".shipping-cost").text(shippingCost);
		$(".total-payment").text(totalAmount);
		
		console.log("Total Quantity:", totalQuantity);
		console.log("Total Amount:", totalAmount);
		console.log("Shipping Cost:", shippingCost);
		
		$(".cart-radio").on("click", function() {
			let checkedCartList = new Array();
			$("input[name=cart]:checked").each(function(){
				let data = new Object();
				data.productId = $(this).data("product-id");
				data.count = $(this).data("product-count");
				data.price = $(this).data("product-price")
				
				checkedCartList.push(data);
			});
			
			// 총 상품수, 상품금액, 총 결제금액 계산
			let totalQuantity = 0;
			let totalAmount = 0;
			checkedCartList.forEach(function(cart) {
				totalQuantity += cart.count;
				totalAmount += cart.count * cart.price;
			});
			
			// 할인금액 등 추가 계산 로직이 있다면 이곳에 추가
			let shippingCost = 3000;
			if (totalAmount > 40000) {
				shippingCost = 0;
			}
			// 총 상품수, 상품금액, 총 결제금액 화면에 표시
			$(".total-quantity").text(totalQuantity);
			$(".total-amount").text(totalAmount);
			$(".shipping-cost").text(shippingCost);
			$(".total-payment").text(totalAmount);
		});
		
		$(".order-btn").on("click", function() {
			
		let jsonData = JSON.stringify(checkedCartList);
		console.log(jsonData);
		
			$.ajax({
				type:"post"
				, url:"/order/create-order"
				, data:jsonData
				, contentType:"application/json"
				
				, success:function(data) {
					location.href = "/checkout/" + data.orderId
				}
				, error:function(request, status, error) {
					alert("구매하기 불가 관리자에게 문의");
				}
			});
		});
	});
</script>