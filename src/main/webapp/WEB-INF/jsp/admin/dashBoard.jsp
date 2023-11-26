<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center p-4">
	<div>
		<a href="/admin/account-manager" class="btn btn-success btn-lg form-control my-3">회원목록</a>
		<a href="#" class="btn btn-success btn-lg form-control my-3">주문목록</a>
		<a href="/admin/product-manager" class="btn btn-success btn-lg form-control my-3">상품관리</a>
		<div class="d-flex align-items-center">
			<input type="text" class="email form-control my-3" placeholder="이메일 입력">
			<button type="button" class="admin-permission-btn btn btn-success">관리자 등록</button>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
    $(".admin-permission-btn").on("click", function() {
		let email = $(".email").val();
		
		$.ajax({
			type:"PUT"
			, url:"/account/admin-permission"
			, data:{"email":email}
		
			, success:function(data) {
				if(data.code == 200) {
					alert("관리자 등록 성공");
				} else if (data.code == 500) {
					alert(errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("관리자 등록 에러");
			}
		});
    });
});
</script>