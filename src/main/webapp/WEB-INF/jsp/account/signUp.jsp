<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center w-100 h-100">
	<div class="sign-box mt-5 p-4">
		<div class="d-flex justify-content-between">
			<input type="text" id="email" class="form-control my-3 col-7" placeholder="이메일을 입력해주세요.">
			<button type="button" class="btn btn-success btn-sm small-font-size p-4">중복확인</button>
		</div>
		<div class="d-flex">
			<input type="text" id="userId" class="form-control my-3" placeholder="아이디를 입력해주세요.">
			<button type="button" class="btn btn-success btn-sm font-size-small">중복확인</button>
		</div>
		<input type="password" id="password" class="form-control my-3" placeholder="비밀번호를 입력해주세요.">
		<input type="password" id="passwordConfirm" class="form-control my-3" placeholder="비밀번호 확인을 위해 다시 입력해주세요.">
		<button type="button" class="btn btn-info form-control">회원가입</button>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 중복 버튼 클릭
		$("#loginIdCheckBtn").on("click", function() {
			// 경고 문구 초기화
			$("#idCheckLength").addClass("d-none");
			$("#idCheckDuplicated").addClass("d-none");
			$("#idCheckOk").addClass("d-none");
			
			let loginId = $("#loginId").val().trim();
			
			if (loginId.length < 4) {
				$("#idCheckLength").removeClass("d-none");
				return;
			}
			// AJAX - 중복확인
			$.ajax({
				// request get(생략)
				url:"/user/is-duplicated-id"
				, data:{"loginId":loginId}
				
				// response
				, success:function(data) {
					// {"code":200, "isDuplicated":true} 중복 true
					if (data.isDuplicated) { // 중복
						$("#idCheckDuplicated").removeClass("d-none");
					} else { // 중복 아님 => 사용 가능
						$("#idCheckOk").removeClass("d-none");
					}
				}
				
				, error:function(request, status, error) {
					alert("중복확인에 실패했습니다.")
				}
			});
			
		});
		
		// 회원가입 submit
		$("#signUpForm").on("submit", function(e) {
			e.preventDefault();  // 서브밋 기능 막음
			
			// validation
			
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			let confirmPassword = $("#confirmPassword").val();
			let name = $("#name").val().trim();
			let email = $("#email").val().trim();
			
			if (!loginId) {
				alert("아이디를 입력하세요");
				return false;  // submit이라 return false
			}
			
			if (!password || !confirmPassword) {
				alert("비밀번호를 입력하세요");
				return false;  
			}
			
			if (password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다");
				return false;
			}
			
			if (!name) {
				alert("이름을 입력하세요");
				return false;  
			}
			
			if (!email) {
				alert("이메일을 입력하세요");
				return false;  
			}
			
			// 중복확인 후 사용 가능한지 확인 => idCheckOk가 d-none이 있을 때 얼럿 띄움
			if ($("#idCheckOk").hasClass('d-none')) {
				alert("아이디 중복확인을 다시 해주세요");
				return false;
			}
			
			// 서버로 보내는 방법 두 가지
			// 1) submit을 자바스크립트로 동작 시컴
			// $(this)[0].submit(); // 화면 이동이 반드시 된다. (jsap, redirect)
			
			// 2) AJAX - 응답값이 JSON
			let url = $(this).attr('action');
			/* alert(url); */
			let params = $(this).serialize(); // 폼태그에 있는 name 속성-값으로 파라미터 구성 ( serialize 직렬화 )
			console.log(params);
			
			$.post(url, params) // request
			.done(function(data) { // response
				// {"code":200, "result":"성공"}
				if (data.code == 200) { // 성공
					alert("가입을 환영합니다. 로그인을 해주세요")
					location.href="/user/sign-in-view" // 로그인 화면으로 이동
					
				} else { // 로직 실패
					alert(data.erorMessage);
				}
			});
			
		});
	}); 
</script>