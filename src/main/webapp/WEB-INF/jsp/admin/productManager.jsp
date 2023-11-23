<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-between">
	<div class="d-flex justify-content-center align-items-center p-4 col-6">
		<div>
				<input type="text" id="name" class="form-control my-3" placeholder="제품명">
				<input type="text" id="brand" class="form-control my-3" placeholder="브랜드">
				<input type="text" id="price" class="form-control my-3" placeholder="가격">
				<input type="text" id="costPrice" class="form-control my-3" placeholder="원가">
				<input type="text" id="stock" class="form-control my-3" placeholder="재고">
				<input type="text" id="content" class="form-control my-3" placeholder="제품설명">
				<input type="text" id="status" class="form-control my-3" placeholder="제품상태">
				<input type="file" id="file" name="file" multiple accept="image/*" class="form-cnotrol my-3">
				<div>
					<img>
				</div>
				<div id="fileName" class=""></div>
				<input type="button" id="saveBtn" class="btn btn-info form-control my-3" value="등록">
		</div>
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
<script>
$(document).ready(function() {
	
	let selectFiles = new Array();
	
	$("#file").on("change", function(e) {
		selectFiles = [];
		let files = e.target.files;
		let filesArr = Array.prototype.slice.call(files);
		console.log(files);
		console.log(filesArr);
		
		filesArr.forEach(function(f) {
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
		//formData.append("fileList", selectFiles);
		//formData.append("imageCount", selectFiles.length);
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