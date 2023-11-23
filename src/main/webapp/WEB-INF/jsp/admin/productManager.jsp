<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-between">
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
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="d-flex justify-content-center align-items-center p-4 col-6">
		<div>
				<div class="d-flex align-items-center">
					<input type="text" id="nameSearch" class="form-control my-3" placeholder="제품명">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" id="brandSearch" class="form-control my-3" placeholder="브랜드">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" id="priceSearch" class="form-control my-3" placeholder="가격">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" id="costPriceSearch" class="form-control my-3" placeholder="원가">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" id="stockSearch" class="form-control my-3" placeholder="재고">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<input type="button" id="editBtn" class="btn btn-warning form-control my-3" value="수정">
				<input type="button" id="deleteBtn" class="btn btn-danger form-control my-3" value="삭제">
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<!-- modal-sm:작은 모달 modal-dialog-centered: 수직 기준 가운데 -->
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content text-center">
			<div class="py-3 border-bottom">
				<a href="#none" id="deletePost">삭제하기</a>
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