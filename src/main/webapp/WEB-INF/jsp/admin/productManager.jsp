<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-between">

	<div class="d-flex justify-content-center align-items-center p-4 col-6">
		<div>이미지</div>
	</div>
	
	<div class="d-flex justify-content-center align-items-center p-4 col-6">
		<table class="table text-center">
			<thead>
				<tr>
					<th>제품번호</th>
					<th>이미지</th>
					<th>제품명</th>
					<th>브랜드</th>
					<th>가격</th>
					<th>원가</th>
					<th>재고</th>
					<th>제품설명</th>
					<th>제품상태</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${productList}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>이미지</td>
					<td>${product.name}</td>
					<td>${product.brand}</td>
					<td>${product.price}</td>
					<td>${product.costPrice}</td>
					<td>${product.stock}</td>
					<td>${product.content}</td>
					<td>${product.status}</td>
					<td>
						<a href="#">수정</a>
					</td>
					<td>
						<a href="#">삭제</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<!-- modal-sm:작은 모달 modal-dialog-centered: 수직 기준 가운데 -->
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content text-center">
			<div class="product-info py-3 border-bottom">
				<div id="resultImg">이미지</div>
				<div id="resultName">제품명</div>
				<div id="resultBrand">브랜드</div>
				<div id="resultPrice">가격</div>
				<div id="resultCostPrice">원가</div>
				<div id="resultStock">재고</div>
				<div id="resultContent">제품설명</div>
				<div id="resultStatus">제품상태</div>
			</div>
			<div class="py-3 border-bottom">
				<a href="#none" id="editProduct">수정하기</a>
			</div>
			<div class="py-3 border-bottom">
				<a href="#none" id="deleteProduct">삭제하기</a>
			</div>
			<div class="py-3">
				<a href="#none" data-dismiss="modal">취소하기</a>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	
	let selectFiles = new Array();
	
	$("#file").on("change", function(e) {
		selectFiles = []; // 초기화
		let files = e.target.files;
		let filesArr = Array.prototype.slice.call(files);
		console.log(files);
		console.log(filesArr);
		
		filesArr.forEach(function(f) { // 이미지파일 체크
			if (!f.type.match("image.*")) {
				alert ("이미지 파일만 업로드하세요.");
				return;
			}
			
			selectFiles.push(f);
		});
		
	});
	
	$("#saveBtn").on("click", function() {
		console.log(selectFiles);
		let formData = new FormData();
		
		let name = $("#name").val().trim();
		let brand = $("#brand").val().trim();
		let price = $("#price").val().trim();
		let costPrice = $("#costPrice").val().trim();
		let stock = $("#stock").val().trim();
		let content = $("#content").val().trim();
		let status = $("#status").val().trim();
		
		for (let i = 0; i < selectFiles.length; i++) {
			console.log(selectFiles[i]);
			formData.append("images", selectFiles[i]);
		}
		console.log(formData);
		formData.append("name", name);
		formData.append("brand", brand);
		formData.append("price", price);
		formData.append("costPrice", costPrice);
		formData.append("stock", stock);
		formData.append("content", content);
		formData.append("status", status);
		
		$.ajax({
			type:"POST"
			, url:"/product/add-product"
			, data:formData
			, enctype:"multipart/form-data"
			, processData:false
			, contentType:false
		
			, success:function(data) {
				if (data.result == "success") {
					alert("저장되었습니다.");
					location.reload(true);
				} else {
					alert("상품 등록 실패");
				}
			}
			, error:function(request, status, error) {
				alert("상품 등록 에러")
			}
		});
	});
	
	
});
</script>