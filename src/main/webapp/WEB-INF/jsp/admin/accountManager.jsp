<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<input type="text" id="email" placeholder="이메일을 입력하세요.">
	<button type="button" id="adminPermissionBtn" class="btn btn-info">관리자승인</button>
</div>

<script>
$(document).ready(function () {
	$("#adminPermissionBtn").on("click", function() {
		let email = $("#email").val().trim();
		
		$.ajax({
			type:"PUT"
			, url:"/account/admin-permission"
			, data:{"email":email}
		
			, success:function(data) {
				if (data.code == 200) {
					alert ("관리자 등록 성공");
				} else if (data.code == 500) {
					alert (data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("관리자 등록 에러 발생");
			}
		});
	});
});
</script>