<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="productWrap" class="d-flex p-4">
	<div id="productImg col-6">
		<img>
	</div>
	<div id="productInfo col-6">
		<div>${product.name}</div>
		<div>${product.brand}</div>
		<div>${product.price}</div>
		<div>${product.content}</div>
	</div>
</div>