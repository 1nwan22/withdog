<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="w-100">
	<div class="d-flex mb-4 p-3">
		<div class="order-progress-text">
			<span class="font-weight-bold">장바구니</span> > 주문결제 > 결제완료
		</div>
	</div>
	<!-- 장바구니 목록 -->
	<div class="d-flex w-100">
		<div class="col-8">
			<div>
		    	<input id="selectAllCheckbox" type="checkbox">
				<label for="selectAllCheckbox">
		            전체선택
		        </label>
			</div>
			<div class="cart-box">
			<c:forEach items="${cartList}" var="cart">
				<div class="d-flex justify-content-between p-2">
						<div class="col-11 d-flex align-items-center justify-content-between">
							<label class="mr-2">
								<input class="cart-radio custom-checkbox" type="checkbox" name="cart" 
								data-product-id="${cart.product.id}" 
								data-product-count="${cart.count}" 
								data-product-price="${cart.product.price}">
								선택
							</label>
							<img src="${cart.productImage.imagePath}" alt="상품썸네일" width="100px" height="100px">
							<div class="ml-4">
								<div>브랜드</div>
								<span>${cart.product.brand}</span>
							</div>
							<div class="ml-5">
								<div>상품명</div>
								<span>상품명: ${cart.product.name}</span>
							</div>
							<div class="cartCount ml-5">
								<button type="button" class="minus-btn">-</button>
								<input type="text" class="count" value="${cart.count}">
								<button type="button" class="plus-btn">+</button>
							</div>
							<span class="ml-5">가격: ${cart.product.price}</span>
						</div>
						<div>
						<div class="d-flex justify-content-center align-items-center">
							
							<button type="button" class="delete-btn">X</button>
						</div>
						</div>
				</div>
			</c:forEach>
			</div>
		</div>
		<!-- 결제 정보 구매하기 > order > checkout -->
		<div class="col-4 pay-info-box p-2">
			
			<div class="px-3 pb-3 pay-info-text2">
			<div class="pay-info-text font-weight-bold">결제정보</div>
			<hr>
				<div>
				상품수: <span class="total-quantity"></span>
				</div>
				<div>
				총 상품금액: <span class="total-amount"></span>원
				</div>
				<div>
				할인금액: <span class="discount"></span>
				</div>
				<div>
				배송비: <span class="shipping-cost"></span>원
				</div>
				<hr>
				<div class="pay-info-text font-weight-bold">
				총 결제금액: <span class="total-payment"></span>원
				</div>
				<button type="button" class="mt-3 order-btn btn">구매하기</button>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
    // 초기 로딩 시 모든 체크박스 선택
    $("#selectAllCheckbox").prop("checked", true);
    $("input[name=cart]").prop("checked", true);
    updateOrderInfo();

    // 각 체크박스 클릭 시 이벤트 처리
    $(".cart-radio").on("click", function() {
        updateOrderInfo();
    });

    // 전체 선택 체크박스 클릭 시 이벤트 처리
    $("#selectAllCheckbox").on("click", function() {
        $("input[name=cart]").prop("checked", $(this).prop("checked"));
        updateOrderInfo();
    });

    // 주문하기 버튼 클릭 시 이벤트 처리
    $(".order-btn").on("click", function() {
        let checkedCartList = [];
        $("input[name=cart]:checked").each(function() {
            let data = {
                productId: $(this).data("product-id"),
                count: $(this).data("product-count"),
                price: $(this).data("product-price")
            };
            checkedCartList.push(data);
        });

        let jsonData = JSON.stringify(checkedCartList);

        $.ajax({
            type: "post",
            url: "/order/create-order",
            data: jsonData,
            contentType: "application/json",
            success: function(data) {
                location.href = "/checkout/" + data.orderId;
            },
            error: function(request, status, error) {
                alert("구매하기 불가 관리자에게 문의");
            }
        });
    });

    // 주문 정보 업데이트 함수
    function updateOrderInfo() {
        let checkedCartList = [];
        $("input[name=cart]:checked").each(function() {
            let data = {
                productId: $(this).data("product-id"),
                count: $(this).data("product-count"),
                price: $(this).data("product-price")
            };
            checkedCartList.push(data);
        });

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
        
        

        $(".total-quantity").text(totalQuantity);
        $(".total-amount").text(totalAmount);
        $(".shipping-cost").text(shippingCost);
        $(".total-payment").text(totalAmount + shippingCost);
    }
    
    /* 수량 버튼 */
	$(".plus-btn, .minus-btn").on("click", function() {
		let currentCount = parseInt($(this).siblings(".count").val());

		// 클릭된 버튼에 따라 수량 조절
		currentCount += ($(this).hasClass("plus-btn") ? 1 : -1);

		// 수량이 1 미만이 되지 않도록 보장
		currentCount = Math.max(currentCount, 1);

		// 결과를 count input에 설정
		$(this).siblings(".count").val(currentCount); 
	});
});
</script>