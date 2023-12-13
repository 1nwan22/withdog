<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<!-- 내가 만든 스타일시트 -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">

<title>함개</title>
</head>
<body>
	<div id="wrap">
		<header>
			<jsp:include page="../include/header.jsp"></jsp:include>
		</header>
		<section class="contents">
			<div class="content-box d-flex">
				<div id="leftSide">
					<jsp:include page="../${viewNameL}.jsp"></jsp:include>
				</div>
				
				<div class="content">
					<jsp:include page="../${viewName}.jsp"></jsp:include>
				</div>
				
				<div id="rightSide">
					<jsp:include page="../${viewNameR}.jsp"></jsp:include>
				</div>
				
			</div>
		</section>
		<footer>
			<jsp:include page="../include/footer.jsp"></jsp:include>
		</footer>
		
		<!-- 글쓰기 모달 -->
		<div class="modal fade" id="modalPostCreate">
			<!-- modal-sm:작은 모달 modal-dialog-centered: 수직 기준 가운데 -->
			<div class="modal-dialog modal-dialog-centered modal-lg">
				<div class="modal-content">
					<div class="post-create p-3 border-bottom">
						<div>
							<div class="py-3 border-bottom">
								새 게시물 만들기
							</div>
							
							<!-- 이미지 업로드 // TODO: 파일 미리보기, drag and drop -->
							<div class="border-bottom">
								<input type="file" class="post-file d-none" multiple accept="image/*"> 
								<a href="#none" class="post-file-btn"> 
									<img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png">
								</a>
								<div id="postFileName"></div>
							</div>
							
							
							<div class="border-bottom">
								<textarea class="post-text-area form-control" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
							</div>
						</div>
						
						<div class="py-3 text-center">
							<button type="button" class="create-post-btn btn btn-primary" data-dismiss="modal">확인</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 글쓰기 모달 끝 -->
	</div>
</body>
<script>
$(document).ready(function() {
	
	$(".post-file-btn").on("click", function(e) {
		e.preventDefault();  // a 태그의 올라가는 현상 방지
		$(".post-file").click();
	});
	
	let selectFiles = new Array();
	let selectFilesName = new Array();
	
	$(".post-file").on("change", function(e) {
		selectFiles = []; // 초기화
		selectFilesName = [];
		$("#postFileName").empty(); // 파일명비우기
		let files = e.target.files;
		let filesArr = Array.prototype.slice.call(files);
		console.log(files);
		console.log(filesArr);
		for (let i = 0; i < filesArr.length; i++) {
			console.log(files[i].name);
			selectFilesName.push(files[i].name);
			$("#postFileName").prepend("<div>" + files[i].name + "</div>");
		}
		if (filesArr.length < 2) {
			$("#postFileName").empty();
		}
		
		filesArr.forEach(function(f) { // 이미지파일 체크
			if (!f.type.match("image.*")) {
				alert ("이미지 파일만 업로드하세요.");
				return;
			}
			
			selectFiles.push(f);
		});
	});
	
	$(".create-post-btn").on("click", function() {
		let content = $(".post-text-area").val().trim();
		console.log(content);
		let formData = new FormData();
		
		for (let i = 0; i < selectFiles.length; i++) {
			console.log(selectFiles[i]);
			formData.append("imageList", selectFiles[i]);
		}
		console.log(formData);
		formData.append("content", content);
		
		$.ajax({
			type:"POST"
			, url:"/post/add"
			, data:formData
			, enctype:"multipart/form-data"
			, processData:false
			, contentType:false
			
			, success:function(data) {
				if (data.result == "success") {
					alert("글 등록 성공");
					location.reload(true);
				} else {
					alert("글 등록 실패");
				}
			}
			, error:function(request, status, error) {
				alert("글 등록 에러")
			}
		});
		
	});
});
</script>
</html>