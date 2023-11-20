<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center w-100">
	<div id="headerBox" class="d-flex justify-content-center">
		<div id="logo">
			<h2>로고</h2>
		</div>
		<div class="nav-group">
			<nav class="nav-box">
				<ul class="nav nav-fill w-100">
					<li class="nav-item"><a href="#" class="nav-link font-weight-bold">sns</a></li>
					<li class="nav-item"><a href="#" class="nav-link">stroe</a></li>
					<li class="nav-item"><a href="#" class="nav-link">place</a></li>
					<c:if test="${empty email}">
						<li class="nav-item"><a href="/account/sign-in-view" class="nav-link">login</a></li>
					</c:if>
					<c:if test="${not empty email}">
						<li class="nav-item"><a href="/account/sign-out" class="nav-link">logout</a></li>
					</c:if>
					
				</ul>
			</nav>
			<div class="sub-nav">
			</div>
		</div>
	</div>
</div>