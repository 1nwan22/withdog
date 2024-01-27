<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="p-2">
	<div class="order-progress-text">
			장바구니 > <span class="font-weight-bold">주문결제</span> > 결제완료
	</div>
	<div class="d-flex">
		<!-- 주문 정보 -->
		<div class="order-info-box">
			<!-- 배송지 정보 박스 -->
			<div class="shipping-info-box p-4">
				<div class="d-flex justify-content-between">
					<div class="shipping-text">배송지</div>
					<button type="button" class="btn shipping-btn">배송지 변경</button>
				</div>
				<div>
					<input type="text" id="receiverName" class="form-control my-2" placeholder="받는 사람">
					<input type="text" id="receiverPhoneNumber" class="form-control my-2" placeholder="전화번호">
					<input type="text" id="receiverAddress1" class="form-control my-2" placeholder="도로명 주소">
					<input type="text" id="receiverAddress2" class="form-control my-2" placeholder="상세 주소">
					<select class="form-control  my-3"> 
					    <option disabled selected value="">배송시 요청사항을 선택해 주세요.</option>
					    <option value="option1">직접 수령하겠습니다</option>
					    <option value="option2">배송 전 연락바랍니다.</option>
					    <option value="option3">부재 시 경비실에 맡겨주세요.</option>
					    <option value="option4">부재 시 문 앞에 놓아주세요.</option>
					    <option value="option5">부재 시 택배함에 넣어주세요.</option>
					    <option value="option6">직접 입력</option>
					</select>
				</div>
			</div>
			
			<!-- 결제수단 박스 -->
			<div class="payment-method-box mt-4 p-4"> 
				<div class="shipping-text">결제수단</div>
				<hr>
				<div>간편결제</div>
				<input type="radio" id="kakaoPay" name="paymethod" value="kakao">
				<label for="kakaoPay">
					<img id="kakaoPayLogo" src="/static/img/kakaopay.jpg">
				</label>
			</div>
		</div>
		<!-- 결제하기 박스 -->
		<div class="payment-info-box p-4">
			<div class="pay-info-text font-weight-bold">
				상품수: <span class="total-quantity"></span>
			</div>
			
			<!-- 주문 상품 정보 -->
				<c:forEach var="orderedProduct" items="${order.orderedProductList}">
				    <div>${orderedProduct.brand}</div>
				    <div class="product-info-box d-flex align-items-center w-100 justify-content-between" data-product-id="${orderedProduct.productId}" data-product-count="${orderedProduct.count}" data-product-price="${orderedProduct.price}">
				        <img src="${orderedProduct.productImagePath}" width="100px" height="100px">
				        <div>상품명: ${orderedProduct.name}</div>
				        <div>상품가격: ${orderedProduct.price}</div>
				        <div>상품수량: ${orderedProduct.count}</div>
				    </div>
				</c:forEach>
			<!-- 결제 정보 -->
			<div class="payment-box p-3" data-order-id="${order.order.id}">
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
				<div class="pay-info-text font-weight-bold my-3">
				총 결제금액: <span class="total-payment"></span>원
				</div>
				<button type="button" class="pay-btn btn my-3">결제하기</button>
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	$(document).ready(function () {
		
		updateOrderInfo();
		
		$(".pay-btn").on("click", function() {
			let paymethod = $("input[name=paymethod]:checked").val();
			console.log(paymethod);
			if (paymethod == "kakao") {
				kakaoPay();
			}
		});
		
		
		function kakaoPay() {
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
		}
		
		$(".shipping-btn").on("click", function() {
			new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                
	                } else {
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById("receiverAddress1").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("receiverAddress2").focus();
	            }
	        }).open();
		});
		
	    function updateOrderInfo() {
	        let checkedCartList = [];
	        $(".product-info-box").each(function() {
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

	});
</script>