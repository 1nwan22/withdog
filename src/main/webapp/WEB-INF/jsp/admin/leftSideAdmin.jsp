<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="leftSideWrap">
	<div>
		<div class="left-side-menu pt-5">
			<div class="my-3"><a href="/">홈</a></div>
			<div class="my-3"><a href="">댕스타</a></div>
			<div class="my-3"><a href="">스토어</a></div>
			<div class="my-3"><a href="">플레이스</a></div>
			<div class="my-3">
				<a href="#" class="" data-toggle="modal" data-target="#modalPostCreate">글쓰기</a>
			</div>
			<div class="my-3"><a href="/my/profile-view">프로필</a></div>
		</div>
		
			
		
		<hr>
		<div class="left-side-admin-menu">
			<div class="my-3"><a href="/admin/dash-board">관리자 페이지</a></div>
			<div class="my-3"><a href="/admin/account-manager">회원관리</a></div>
			<div class="my-3"><a href="/admin/order-manager">주문관리</a></div>
			<div class="my-3"><a href="/admin/product-manager">상품관리</a></div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
/* 	let currentPosition = parseInt($("#leftSideWrap").css("top"));
	$(window).scroll(function() {
		let position = $(window).scrollTop(); 
		$("#leftSideWrap").stop().animate({"top":position+currentPosition+"px"},500);
	}); */
});
</script>