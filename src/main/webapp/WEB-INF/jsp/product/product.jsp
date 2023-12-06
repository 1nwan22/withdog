<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="productWrap" class="p-4">
	<!-- 상품 정보 -->
	<div class="d-flex w-100">
		<div id="productImg" class="col-6">
			<img src="${product.productImage.imagePath}" class="w-100" alt="상품대표이미지">
		</div>
		<div id="productInfo" class="col-6">
			<div>${product.product.name}</div>
			<div>${product.product.brand}</div>
			<div>${product.product.price}</div>
			<div>${product.product.content}</div>
			<div>별점</div>
			<div>리뷰 수</div>
			<div>
		        <button type="button" class="minus-btn">-</button>
		        <input type="text" id="count" value="0" readonly>
				<button type="button" class="plus-btn">+</button>
			</div>
			<button type="button" class="addCart btn btn-success" data-product-id="${product.product.id}">장바구니 추가</button>
		</div>
	</div>
	<!-- 상품 상세 정보 박스 -->
	<div>
		<!-- 상품 상세 정보 메뉴 -->
		<nav class="product-detail-menu">
			<ul class="nav nav-fill w-100">
				<li class="nav-item">
					<a href="#none" class="nav-link font-weight-bold">상세설명</a>
				</li>
				<li class="nav-item">
					<a href="#none" class="nav-link font-weight-bold">상품평</a>
				</li>
				<li class="nav-item">
					<a href="#none" class="nav-link font-weight-bold">상품문의</a>
				</li>
				<li class="nav-item">
					<a href="#none" class="nav-link font-weight-bold">교환/반품</a>
				</li>
			</ul>
		</nav>
		<!-- 상품 상세 정보 메뉴 끝 -->
		
		<!-- 상품 상세 정보 -->
		<div>
		
		</div>
	</div>
	<!-- 상품 상세 정보 박스 끝 -->
</div>


<script>
	$(document).ready(function () {
		
		/* 메뉴 고정 */
		let Offset = $('.product-detail-menu').offset();
		$(window).scroll( function() {
			if ($(document).scrollTop() > Offset.top ) {
		      $('.product-detail-menu').addClass('fixed');
		    }
		    else {
		      $('.product-detail-menu').removeClass('fixed');
		    }
		});
		
		/* 장바구니 추가 */
		$(".addCart").on("click", function() {
			let productId = $(this).data("product-id");
			let count = $("#count").val();
			
			if (count < 1) {
				alert("수량을 선택해주세요");
				return;
			}
			
			$.ajax({
				type:"POST"
				, url:"/cart/add"
				, data:{"productId":productId, "count":count}
			
				, success:function(data) {
					alert("장바구니 추가 완료");
					location.reload(true);
				}
				, error:function(request, status, error) {
					alert("장바구니 추가 실패");
				}
			});
		});
		
		/* 수량 버튼 */
		$(".plus-btn, .minus-btn").on("click", function() {
		    let currentCount = parseInt($("#count").val());

		    // 클릭된 버튼에 따라 수량 조절
		    currentCount += ($(this).hasClass("plus-btn") ? 1 : -1);

		    // 수량이 1 미만이 되지 않도록 보장
		    currentCount = Math.max(currentCount, 1);

		    // 결과를 #count input에 설정
		    $("#count").val(currentCount);
		});
		
		  
		
	});

</script>