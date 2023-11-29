<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="productListWrap">
	<div>카테고리별 나누기</div>
	<!-- 상품 목록 시작 -->
	<div class="product-boxes d-flex justify-content-between">
		<c:forEach items="${productList.content}" var="product">
			<div class="product-box" data-product-id="${product.product.id}">
				<img src="${product.productImage.imagePath}" class="w-100" alt="상품대표이미지">
				<div class="product-box-info">
					<div>${product.product.brand}</div>
					<div>${product.product.name}</div>
					<hr>
					<div>${product.product.price}원</div>
				</div>
			</div>
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
		
		$(".product-box").on()
	});

</script>