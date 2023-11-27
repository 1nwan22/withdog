<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center w-100 h-100">
	<div class="sign-box mt-5 p-4">
		<button type="button" class="btn btn-success form-control my-3">네이버로 로그인</button>
		<button type="button" class="btn btn-warning form-control my-3"><a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}">카카오로 로그인</a></button>
		<button type="button" class="btn btn-light form-control my-3">구글로 로그인</button>
		<hr>
		<input type="text" id="email" class="form-control my-3 d-none" placeholder="이메일을 입력하세요">
		<input type="password" id="password" class="form-control my-3 d-none" placeholder="비밀번호를 입력하세요">
		<button type="button" id="emailLogInBtn" class="btn btn-secondary form-control my-3">이메일로 로그인</button>
		<div class="d-flex justify-content-between">
			<span><a href="#">아이디 / 비밀번호 찾기</a></span>
			<span><a href="/account/sign-up-view">회원가입</a></span>
		</div>
	</div>
</div>

<script>
 $(document).ready(function () {
	 $("#emailLogInBtn").on("click", function() {
		 if ($("#email").hasClass("d-none") && $("#password").hasClass("d-none")) {
			 $("#email").removeClass("d-none");
			 $("#password").removeClass("d-none");
			 return;
		 }
		 
		 let email = $("#email").val().trim();
		 let password = $("#password").val();
		 
		 if (!email) {
			 alet("이메일을 입력하세요.");
			 return;
		 }
		 
		 if (!password) {
			 alet("비밀번호를 입력하세요.");
			 return;
		 }
		 
		 $.ajax({
			type:"post"
			 , url:"/account/sign-in"
			 , data:{"email":email, "password":password}
		 	
		 	 , success:function(data) {
		 		 alert("로그인 성공");
		 		 location.href = "/";
		 	 }
		 	 , error:function(data) {
		 		 alert("로그인 실패");
		 	 }
		 	
		 });
	 });
 });
</script>


