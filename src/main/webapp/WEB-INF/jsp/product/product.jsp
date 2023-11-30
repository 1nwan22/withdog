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
		
		 let Offset = $('.product-detail-menu').offset();
		  $(window).scroll( function() {
		    if ($(document).scrollTop() > Offset.top ) {
		      $('.product-detail-menu').addClass('fixed');
		    }
		    else {
		      $('.product-detail-menu').removeClass('fixed');
		    }
		  });
		
		$(".product-box").on()
	});

</script>