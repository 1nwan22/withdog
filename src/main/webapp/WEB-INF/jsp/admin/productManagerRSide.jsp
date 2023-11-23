<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="d-flex justify-content-center align-items-center">
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
	<hr>
	<div class="d-flex justify-content-center align-items-center">
		<div>
				<div class="d-flex align-items-center">
					<input type="text" id="nameSearch" class="form-control my-3" placeholder="제품명">
					<input type="button" class="search-btn btn btn-success py-0" data-toggle="modal" data-target="#modal" data-product-id="" value="검색">
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
		</div>
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
	
	$(".search-btn").on("click", function () {
		let targetProductValue = $(this).prev().val().trim();
		let targetProductKey = $(this).prev().attr("id").replace("Search", "");
		console.log(targetProductKey);
		console.log(targetProductValue);
		
		$.ajax({
			url:"/product/get-product"
			, data:{"targetProductKey":targetProductKey, "targetProductValue":targetProductValue}
		
			, success:function(data) {
				if (data.code == 200) {
					$("#resultName").text(data.product.name);
				} else if (data.code == 500) {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("검색 에러");
			}
		});
	});
	
	$("#editProduct").on("click", function() {
		alert("edit");
	});
	
	$("#deleteProduct").on("click", function() {
		alert("del");
	});
	
	
	
});
</script>