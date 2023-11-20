<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center w-100 h-100">
	<div class="sign-box mt-5 p-4">
		<form id="signUpForm" method="post" action="/account/sign-up">
			<div class="my-4">
				<div class="d-flex justify-content-between align-items-center">
					<input type="text" id="email" name="email"
						class="form-control col-9" placeholder="이메일을 입력해주세요.">
					<button type="button" id="emailCehckBtn"
						class="btn btn-success col-2 btn-sm">중복확인</button>
				</div>
				<%-- 아이디 체크 결과 --%>
				<%-- d-none 클래스: display none (보이지 않게) --%>
				<div id="emailCheckLength" class="check-text text-danger d-none">email을
					4자 이상 입력해주세요.</div>
				<div id="emailCheckDuplicated" class="check-text text-danger d-none">이미
					사용중인 email입니다.</div>
				<div id="emailCheckOk" class="check-text text-success d-none">사용
					가능한 email 입니다.</div>
			</div>
			<div class="my-4">
				<div class="d-flex justify-content-between align-items-center">
					<input type="text" id="userId" name="userId"
						class="form-control col-9" placeholder="아이디를 입력해주세요.">
					<button type="button" id="userIdCheckBtn"
						class="btn btn-success btn-sm col-2">중복확인</button>
				</div>

				<%-- 아이디 체크 결과 --%>
				<%-- d-none 클래스: display none (보이지 않게) --%>
				<div id="idCheckLength" class="check-text text-danger d-none">ID를
					4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="check-text text-danger d-none">이미
					사용중인 ID입니다.</div>
				<div id="idCheckOk" class="check-text text-success d-none">사용
					가능한 ID 입니다.</div>
			</div>

			<input type="userName" id="userName" name="userName"
				class="form-control my-4" placeholder="이름을 입력해주세요."> <input
				type="password" id="password" name="password"
				class="form-control my-4" placeholder="비밀번호를 입력해주세요."> <input
				type="password" id="passwordConfirm" name="passwordConfirm"
				class="form-control my-4" placeholder="비밀번호 확인을 위해 다시 입력해주세요.">
			<button type="submit" id="signUpBtn"
				class="btn btn-info form-control">회원가입</button>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 중복 버튼 클릭
		$("#emailCehckBtn").on("click", function() {

			// 경고 문구 초기화
			$("#emailCheckLength").addClass("d-none");
			$("#emailCheckDuplicated").addClass("d-none");
			$("#emailCheckOk").addClass("d-none");

			let email = $("#email").val().trim();

			if (email.length < 4) {
				$("#emailCheckLength").removeClass("d-none");
				return;
			}
			// AJAX - 중복확인
			$.ajax({
				// request get(생략)
				url : "/account/is-duplicated-email",
				data : {
					"email" : email
				}

				// response
				,
				success : function(data) {
					// {"code":200, "isDuplicated":true} 중복 true
					if (data.isDuplicated) { // 중복
						$("#emailCheckDuplicated").removeClass("d-none");
					} else { // 중복 아님 => 사용 가능
						$("#emailCheckOk").removeClass("d-none");
					}
				}

				,
				error : function(request, status, error) {
					alert("중복확인에 실패했습니다.")
				}
			});

		});

		$("#userIdCheckBtn").on("click", function() {

			// 경고 문구 초기화
			$("#idCheckLength").addClass("d-none");
			$("#idCheckDuplicated").addClass("d-none");
			$("#idCheckOk").addClass("d-none");

			let userId = $("#userId").val().trim();

			if (email.length < 4) {
				$("#idCheckLength").removeClass("d-none");
				return;
			}
			// AJAX - 중복확인
			$.ajax({
				// request get(생략)
				url : "/account/is-duplicated-user-id",
				data : {
					"userId" : userId
				}

				// response
				,
				success : function(data) {
					// {"code":200, "isDuplicated":true} 중복 true
					if (data.isDuplicated) { // 중복
						$("#idCheckDuplicated").removeClass("d-none");
					} else { // 중복 아님 => 사용 가능
						$("#idCheckOk").removeClass("d-none");
					}
				}

				,
				error : function(request, status, error) {
					alert("중복확인에 실패했습니다.")
				}
			});

		});

		// 회원가입 submit
		$("#signUpForm").on("submit", function(e) {
			e.preventDefault();
			// validation

			let email = $("#email").val().trim();
			let userId = $("#userId").val().trim();
			let userName = $("#userName").val().trim();
			let password = $("#password").val();
			let passwordConfirm = $("#passwordConfirm").val();

			if (!email) {
				alert("이메일을 입력하세요");
				return false;
			}

			if (!userId) {
				alert("아이디를 입력하세요");
				return false; // submit이라 return false
			}

			if (!userName) {
				alert("이름을 입력하세요");
				return false; // submit이라 return false
			}

			if (!password || !passwordConfirm) {
				alert("비밀번호를 입력하세요");
				return false;
			}

			if (password != passwordConfirm) {
				alert("비밀번호가 일치하지 않습니다");
				return false;
			}

			// 중복확인 후 사용 가능한지 확인 => idCheckOk가 d-none이 있을 때 얼럿 띄움
			if ($("#emailCheckOk").hasClass('d-none')) {
				alert("이메일 중복확인을 다시 해주세요");
				return false;
			}

			if ($("#idCheckOk").hasClass('d-none')) {
				alert("아이디 중복확인을 다시 해주세요");
				return false;
			}

			let url = $(this).attr("action");
			console.log(url);
			let params = $(this).serialize();
			console.log(params);

			$.post(url, params).done(function(data) {
				if (data.code == 200) {
					alert("가입을 환영합니다");
					location.href = "/account/sign-in-view"
				} else {
					alert(data.errorMessage);
				}
			});

		});
	});
</script>