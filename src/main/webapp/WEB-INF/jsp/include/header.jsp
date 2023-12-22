<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex flex-row w-100">
	<div id="logoWrap" class="header-side">
		<div id="logo" class="d-flex justify-content-center align-items-center">
			<a href="/">
				<img src="/static/img/logo_withdog.jpg" alt="logo">
			</a>
		</div>
	</div>
	<!-- 메뉴 그룹 시작 -->
		<div class="nav-group">
			<!-- 메인 메뉴 시작 -->
			<div id="mainNav">
				<nav>
					<ul class="nav nav-fill w-100">
						<li class="nav-item d-flex justify-content-center align-items-center">
							<a href="#" class="nav-link font-weight-bold">댕스타</a>
						</li>
						<li class="nav-item d-flex justify-content-center align-items-center">
							<a href="/product/list-view" class="nav-link font-weight-bold">스토어</a>
						</li>
						<li class="nav-item d-flex justify-content-center align-items-center" >
							<a href="#" class="nav-link font-weight-bold">플레이스</a>
						</li>
						<c:if test="${empty email}">
						<li class="nav-item d-flex justify-content-center align-items-center">
							<a href="/account/sign-in-view" class="nav-link font-weight-bold">로그인</a>
						</li>
						</c:if>
						<c:if test="${not empty email}">
						<li class="nav-item d-flex justify-content-center align-items-center">
							<a href="#none" id="myPage" class="on nav-link font-weight-bold">마이 페이지</a>
						</li>
						</c:if>
					</ul>
				</nav>
			</div>
			<!-- 메인 메뉴 끝 -->
			<!-- 서브 메뉴 시작 -->
				<div class="sub-nav">
					<c:if test="${not empty email}">
						<nav id="myPageNav" class="d-none on">
							<ul class="nav nav-fill w-100">
								<li class="nav-item">
									<a href="/account/profile-view" class="nav-link">프로필</a>
								</li>
								<li class="nav-item">
									<a href="/order/recent-history-view" class="nav-link">쇼핑 정보</a>
								</li>
								<li class="nav-item">
									<a href="/cart" class="nav-link">장바구니</a>
								</li>
								<li class="nav-item">
									<a href="/account/sign-out" class="nav-link">로그아웃</a>
								</li>
							</ul>
						</nav>
					</c:if>
				</div>
			<!-- 서브 메뉴 끝 -->
		</div>
	<!-- 메뉴 그룹 끝 -->
	<div class="header-side">
	
	</div>
</div>

<script>
$(document).ready(function () {
	
	$("#myPage").on("click", function () {
		
		$("#myPageNav").removeClass("d-none");
		
	});
});
</script>