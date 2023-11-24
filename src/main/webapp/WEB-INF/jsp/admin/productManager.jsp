<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-between">

	<div class="d-flex justify-content-center align-items-center p-4 col-3">
		<div>이미지</div>
	</div>
	
	<div class="d-flex justify-content-center align-items-center p-4 col-9">
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
			    <c:forEach items="${products.content}" var="product">
			        <tr>
			            <td>
			            	<a href="/product/${product.id}">
			            		${product.id}
			            	</a>
			            </td>
			            <td>이미지</td>
			            <td>${product.name}</td>
			            <td>${product.brand}</td>
			            <td>${product.price}</td>
			            <td>${product.costPrice}</td>
			            <td>${product.stock}</td>
			            <td>${product.content}</td>
			            <td>${product.status}</td>
			            <td>
			                <button type="button" class="edit-product-btn btn btn-warning btn-sm" data-toggle="modal"  data-target="#modal">수정</button>
			            </td>
			            <td>
			                <button type="button" class="delete-product-btn btn btn-danger btn-sm" data-product-id="${product.id}">삭제</button>
			            </td>
			        </tr>
			        <!-- Modal -->
					<div class="modal fade" id="modal">
						<!-- modal-sm:작은 모달 modal-dialog-centered: 수직 기준 가운데 -->
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="product-info p-3 border-bottom">
									<span>이름</span><input type="text" id="editName" class="edit-product form-control my-3" value="${product.name}">
									<span>브랜드</span><input type="text" id="editBrand" class="edit-product form-control my-3" value="${product.brand}">
									<span>가격</span><input type="text" id="editPrice" class="edit-product form-control my-3" value="${product.price}">
									<span>원가</span><input type="text" id="editCostPrice" class="edit-product form-control my-3" value="${product.costPrice}">
									<span>재고</span><input type="text" id="editStock" class="edit-product form-control my-3" value="${product.stock}">
									<span>설명</span><input type="text" id="editContent" class="edit-product form-control my-3" value="${product.content}">
									<span>상태</span><input type="text" id="editStatus" class="edit-product form-control my-3" value="${product.status}">
									<input type="file" id="editFile" name="file" multiple accept="image/*" class="edit-product form-cnotrol my-3">
								</div>
								<div class="py-3 border-bottom text-center">
									<a href="#none" id="editProduct">수정하기</a>
								</div>
								<div class="py-3 text-center">
									<a href="#none" data-dismiss="modal">취소하기</a>
								</div>
							</div>
						</div>
					</div>
			    </c:forEach>
			</tbody>
		</table>
	</div>
	
	
</div>



<script>
$(document).ready(function() {
	
	$(".delete-product-btn").on("click", function() {
		let productId = $(this).data("product-id");
		console.log(productId);
		
		$.ajax({
			type:"DELETE"
			, url:"/product/delete-product"
			, data:{"productId":productId}
		
			, success:function(data) {
				if (data.code == 200) {
					location.reload(true);
				} else if (data.code == 500) {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("삭제 실패");
			}
		});
	});
	
	$("#editProduct").on("click", function() {
		editName
		editBrand
		editPrice
		editCostPrice
		editStock
		editContent
		editStatus
		editFile
		
		
		
	});
	
	
	
});
</script>