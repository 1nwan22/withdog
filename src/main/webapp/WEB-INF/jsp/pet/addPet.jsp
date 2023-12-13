<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<h1>펫 등록</h1>
	<div class="d-flex justify-content-center align-items-center">
		<div>
			<input type="text" id="petName" class="form-control my-3" placeholder="이름">
			<input type="text" id="petAge" class="form-control my-3" placeholder="나이">
			<input type="text" id="petspecies" class="form-control my-3" placeholder="견종">
			<div>
				<input type="radio" id="petMale" value="m" name="petGender" checked>
				<label for="petMale">수컷</label>
				<input type="radio" id="petFemale" value="f" name="petGender">
				<label for="petFemale">암컷</label>
			</div>
			<div>
				<span>중성화</span>
				<input type="radio" id="yesNeutral" value="y" name="isNeutralization" checked>
				<label for="yesNeutral">예</label>
				<input type="radio" id="noNeutrual" value="n" name="isNeutralization">
				<label for="noNeutrual">아니오</label>
			</div>
			<input type="file" name="file" multiple accept="image/*" class="file form-cnotrol mt-3">
			<div class="file-name"></div>
			<input type="button" id="savePetProfileBtn" class="btn btn-info form-control my-3" value="등록">
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#savePetProfileBtn").on("click", function() {
			let name = $("#petName").val().trim();
			let age = $("#petAge").val().trim();
			let species = $("#petspecies").val().trim();
			
			let gender = $("input[name='petGender']:checked").val();
			let neutralization = $("input[name='isNeutralization']:checked").val();
			
			console.log(name);
			console.log(age);
			console.log(species);
			console.log(gender);
			console.log(neutralization);
			
			/* age는 birthday datepicker로 수정 고려 */
			// 유효성 검사
			if (!name) {
				alert("이름을 입력하세요");
				return;
			}
			if (!age) {
				alert("나이를 입력하세요");
				return;
			}
			if (!species) {
				alert("견종을 입력하세요");
				return;
			}
			if (isNaN(age)) {
            	alert("나이는 숫자로 입력하세요.");
           		return;
      		}
			
			
			$.ajax({
				type:"post"
				, url:"/pet/add"
				, data:{
					"name":name
					, "age":age
					, "species":species
					, "gender":gender
					, "neutralization":neutralization
				}
				
				, success:function(data) {
					alert("등록 성공");
					location.href="/pet/profile-view"
				}
				, error:function(request, status, error) {
					alert("error");
				}
			});
		});
	});
</script>