<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-between">
	<div class="d-flex justify-content-center align-items-center p-4 col-6">
		<div>
			<form id="addProduct" method="post" action="">
				<input type="text" name="name" class="form-control my-3" placeholder="제품명">
				<input type="text" name="brand" class="form-control my-3" placeholder="브랜드">
				<input type="text" name="price" class="form-control my-3" placeholder="가격">
				<input type="text" name="costPrice" class="form-control my-3" placeholder="원가">
				<input type="text" name="stock" class="form-control my-3" placeholder="재고">
				<input type="text" name="content" class="form-control my-3" placeholder="제품설명">
				<input type="text" name="status" class="form-control my-3" placeholder="제품상태">
				<input type="file" name="file" multiple class="form-cnotrol my-3" placholder="제품이미지">
				<input type="button" id="saveBtn" class="btn btn-info form-control my-3" value="등록">
			</form>
		</div>
	</div>
	
	<div class="d-flex justify-content-center align-items-center p-4 col-6">
		<div>
			<form id="getProduct" method="post" action="">
				<div class="d-flex align-items-center">
					<input type="text" name="name" class="form-control my-3" placeholder="제품명">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" name="brand" class="form-control my-3" placeholder="브랜드">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" name="price" class="form-control my-3" placeholder="가격">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" name="costPrice" class="form-control my-3" placeholder="원가">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<div class="d-flex align-items-center">
					<input type="text" name="stock" class="form-control my-3" placeholder="재고">
					<input type="button" class="search-btn btn btn-success py-0" value="검색">
				</div>
				<input type="button" id="editBtn" class="btn btn-warning form-control my-3" value="수정">
				<input type="button" id="deleteBtn" class="btn btn-danger form-control my-3" value="삭제">
			</form>
		</div>
	</div>
</div>
<script>

</script>