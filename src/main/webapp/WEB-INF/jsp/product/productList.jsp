<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="productListWrap" class="d-flex justify between">
	<c:forEach items="${productList}" var="product">
		<table>
			<thead>
				<tr>
					<th>이름</th>
					<th>브랜드</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${product.name}</td>
					<td>${product.brand}</td>
					<td>${product.price}</td>
				</tr>
			</tbody>
		</table>
		
	</c:forEach>
</div>