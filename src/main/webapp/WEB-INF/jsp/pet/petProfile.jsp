<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="p-4">
		<!-- 펫 프로필 -->
		<div class="">
			<table class="table">
				<thead>
					<tr>
						<th>프로필 이미지</th>
						<th>이름</th>
						<th>나이</th>
						<th>견종</th>
						<th>성별</th>
						<th>중성화</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${petList}" var="pet">
						<tr>
							<td>
								<img src="${pet.profileImagePath}" alt="펫프로필" width="150px">
							</td>
							<td>${pet.name}</td>
							<td>${pet.age}</td>
							<td>${pet.species}</td>
							<td>${pet.gender}</td>
							<td>${pet.neutralization}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
			
		<div class="d-flex justify-content-center">
			<a href="/pet/profile-edit-view" class="btn btn-success form-control col-3">펫 정보 수정</a>
		</div>
	</div>	
		
</div>