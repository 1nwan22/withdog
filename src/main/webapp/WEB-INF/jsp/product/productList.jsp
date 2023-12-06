<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="productListWrap" class="">
	<!-- 상품 카테고리 -->
	<nav class="product-category-menu">
		<ul class="nav nav-fill w-100">
			<li class="nav-item">
				<a href="#none" class="nav-link font-weight-bold">사료</a>
			</li>
			<li class="nav-item">
				<a href="#none" class="nav-link font-weight-bold">패드</a>
			</li>
			<li class="nav-item">
				<a href="#none" class="nav-link font-weight-bold">간식</a>
			</li>
			<li class="nav-item">
				<a href="#none" class="nav-link font-weight-bold">옷</a>
			</li>
			<li class="nav-item">
				<a href="#none" class="nav-link  font-weight-bold">켄넬</a>
			</li>
		</ul>
	</nav>
	<!-- 상품 목록 시작 -->
	<div class="product-boxes d-flex flex-wrap justify-content-between p-3">
		<c:forEach items="${productList.content}" var="product">
			<a href="/product/${product.product.id}" class="product-box mt-3" data-product-id="${product.product.id}">
				<div class="d-flex justify-content-center w-100">
					<img src="${product.productImage.imagePath}" width="312px" alt="상품대표이미지">
				</div>
				
				<div class="product-box-info">
					<div>${product.product.brand}</div>
					<div>${product.product.name}</div>
					<hr>
					<div>${product.product.price}원</div>
					<button type="button" class="addCart btn btn-success">장바구니 추가</button>
				</div>
			</a>
		</c:forEach>
	</div>
	<!-- 상품 목록 끝 -->
	
	<!-- paging 시작 -->
	<div class="text-xs-center">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${productList.first}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/product/list-view/?page=0">&lt;&lt;</a></li>
					<li class="page-item"><a class="page-link" href="/product/list-view/?page=${productList.number - 1}">&lt;</a></li>
				</c:otherwise>
			</c:choose>
			
			<c:forEach begin="${minBundlePage}" end="${maxBundlePage}" var="currentPage">
				<c:url var="pageUrl" value="/product/list-view/">
					<c:param name="page" value="${currentPage - 1}"/>
				</c:url>
					
				<li class="page-item ${productList.number + 1 == currentPage ? 'active' : ''}">
					<a class="page-link" href="${pageUrl}">${currentPage}</a>
				</li>
			</c:forEach>
			
			<c:choose>
				<c:when test="${productList.last}"></c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/product/list-view/?page=${productList.number + 1}">&gt;</a></li>
					<li class="page-item"><a class="page-link" href="/product/list-view/?page=${productList.totalPages - 1}">&gt;&gt;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- paging 끝 -->
</div>

<script>
	$(document).ready(function () {
		
		 let Offset = $('.product-category-menu').offset();
		  $(window).scroll( function() {
		    if ($(document).scrollTop() > Offset.top ) {
		      $('.product-category-menu').addClass('fixed');
		    }
		    else {
		      $('.product-category-menu').removeClass('fixed');
		    }
		  });
		
		$(".product-box").on()
	});

</script>