<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="productWrap">
	<!-- 상품 정보 -->
	<div class="d-flex w-100 p-4">
		<div id="productImg" class="col-6">
			<img src="${product.productImage.imagePath}" class="w-100"
				alt="상품대표이미지">
		</div>
		<div id="productInfo" class="col-6">
			<div>${product.product.name}</div>
			<div>${product.product.brand}</div>
			<div>${product.product.price}</div>
			<div>${product.product.content}</div>
			<div>별점</div>
			<div>리뷰 수</div>
			<div>
				<button type="button" class="minus-btn">-</button>
				<input type="text" class="count" value="0">
				<button type="button" class="plus-btn">+</button>
			</div>
			<button type="button" class="add-cart cart-img" id="addCartBtn"
				data-product-id="${product.product.id}"></button>
			<button type="button" class="favorite-button button-favorite"></button>
		</div>
	</div>
	<!-- 상품 상세 정보 박스 -->
	<div>
		<!-- 상품 상세 정보 메뉴 -->
		<nav class="product-menu-detail">
			<ul class="nav nav-fill w-100">
				<li class="nav-item product-menu-tab">
					<a href="#tabDetail" class="nav-link font-weight-bold">상세설명</a>
				</li>
				<li class="nav-item product-menu-tab">
					<a href="#tabReview" class="nav-link font-weight-bold">상품평</a>
				</li>
				<li class="nav-item product-menu-tab">
					<a href="#tabInquiry" class="nav-link font-weight-bold">상품문의</a>
				</li>
				<li class="nav-item product-menu-tab">
					<a href="#tabChange" class="nav-link font-weight-bold">교환/반품</a>
				</li>
			</ul>
		</nav>
		<!-- 상품 상세 정보 메뉴 끝 -->

		<!-- 상품 상세 정보 -->
		<div class="p-4">
			<!-- 상세 설명 -->
			<div id="tabDetail" class="product-tab">
				<div id="detailImageWrap">
					<c:forEach items="${product.productImageList}" var="productImage">
						<div class="d-flex justify-content-center">
							<img src="${productImage.imagePath}" class="my-4" width="400px">
						</div>
					</c:forEach>
				</div>
				<div class="text-center">
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
					<h1>상품 상세 설명</h1>
				</div>
			</div>

			<!-- 상품평 -->
			<div id="tabReview" class="product-tab d-none">
				<c:forEach items="${product.reviewPage.content}" var="review">
					<div class="">
						<div class="d-flex">
							<div>
							${review.userId}
							</div>
							<div class="star-rating">
								 <c:choose>
							        <c:when test="${review.point == 0}">
								     	<div class="star-empty"></div>
								        <div class="star-empty"></div>
								        <div class="star-empty"></div>
								        <div class="star-empty"></div>
								        <div class="star-empty"></div>
							        </c:when>
							        <c:when test="${review.point == 1}">
							            <div class="star-filled"></div>
							            <div class="star-empty"></div>
							            <div class="star-empty"></div>
							            <div class="star-empty"></div>
							            <div class="star-empty"></div>
							        </c:when>
							        <c:when test="${review.point == 2}">
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-empty"></div>
							            <div class="star-empty"></div>
							            <div class="star-empty"></div>
							        </c:when>
							        <c:when test="${review.point == 3}">
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-empty"></div>
							            <div class="star-empty"></div>
							        </c:when>
							        <c:when test="${review.point == 4}">
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-empty"></div>
							        </c:when>
							        <c:otherwise>
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							            <div class="star-filled"></div>
							        </c:otherwise>
							    </c:choose>
							</div>
							<div>
							${review.createdAt}
							</div>
						</div>
						<div>
							${review.content}
						</div>
					</div>
				</c:forEach>
			</div>

			<!-- 상품문의 -->
			<div id="tabInquiry" class="product-tab d-none">
				<div>
					<div class="d-flex justify-content-between my-3">
						<div class="inquiry-text">상품문의</div>
						<div>
						<input id="myInquiry" type="radio" name="inquiry">
						<label for="myInquiry">내 문의</label> 
						<input id="totalInquiry" type="radio" name="inquiry">
						<label for="totalInquiry">전체 문의</label> 
						<button class="product-inquiry-btn btn"
							data-toggle="modal" data-target="#modalProductInquriy">문의하기</button>
						</div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>번호</th>
								<th>문의종류</th>
								<th>답변상태</th>
								<th>제목</th>
								<th>문의자</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${product.inquiryPage.content}"
								var="productInquiry">
								<tr>
									<td>${productInquiry.id}</td>
									<td>${productInquiry.category}</td>
									<td>${productInquiry.status}</td>
									<td>${productInquiry.subject}</td>
									<td>${productInquiry.userId}</td>
									<td>${productInquiry.createdAt}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<!-- paging 시작 -->
					<div class="text-xs-center">
						<ul class="pagination justify-content-center">
							<c:choose>
								<c:when test="${product.inquiryPage.first}"></c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/product/${product.product.id}/?page=0">&lt;&lt;</a></li>
									<li class="page-item"><a class="page-link" href="/product/${product.product.id}/?page=${product.inquiryPage.number - 1}">&lt;</a></li>
								</c:otherwise>
							</c:choose>
							
							<c:forEach begin="${minBundlePage}" end="${maxBundlePage}" var="currentPage">
								<c:url var="pageUrl" value="/product/${product.product.id}/">
									<c:param name="page" value="${currentPage - 1}"/>
								</c:url>
									
								<li class="page-item ${product.inquiryPage.number + 1 == currentPage ? 'active' : ''}">
									<a class="page-link" href="${pageUrl}">${currentPage}</a>
								</li>
							</c:forEach>
							
							<c:choose>
								<c:when test="${product.inquiryPage.last}"></c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="/product/${product.product.id}/?page=${product.inquiryPage.number + 1}">&gt;</a></li>
									<li class="page-item"><a class="page-link" href="/product/${product.product.id}/?page=${product.inquiryPage.totalPages - 1}">&gt;&gt;</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<!-- paging 끝 -->



					<!-- 문의하기 모달 -->
					<div class="modal fade" id="modalProductInquriy">
						<!-- modal-sm:작은 모달 modal-dialog-centered: 수직 기준 가운데 -->
						<div class="modal-dialog modal-dialog-centered modal-lg">
							<div class="modal-content">
								<div class="product-info p-3 border-bottom">
									<div>
										<h2>판매자에게 문의하기</h2>
										<hr>
										<table>
											<tbody>
												<tr>
													<th>문의종류</th>
													<td>
														<ul>
															<li><span> <em><input type="radio"
																		id="ic1" name="inquiryCategory" class="" value="1"
																		checked></em> <label for="ic1">상품</label>
															</span></li>
															<li><span> <em><input type="radio"
																		id="ic2" name="inquiryCategory" class="" value="2"></em>
																	<label for="ic2">배송</label>
															</span></li>
															<li><span> <em><input type="radio"
																		id="ic3" name="inquiryCategory" class="" value="3"></em>
																	<label for="ic3">취소</label>
															</span></li>
															<li><span> <em><input type="radio"
																		id="ic4" name="inquiryCategory" class="" value="4"></em>
																	<label for="ic4">반품</label>
															</span></li>
															<li><span> <em><input type="radio"
																		id="ic5" name="inquiryCategory" class="" value="5"></em>
																	<label for="ic5">교환</label>
															</span></li>
															<li><span> <em><input type="radio"
																		id="ic6" name="inquiryCategory" class="" value="6"></em>
																	<label for="ic6">기타</label>
															</span></li>

														</ul>
													</td>
												</tr>
												<tr>
													<th>상품명</th>
													<td>${product.product.name}</td>
												</tr>
												<tr>
													<th>아이디</th>
													<td></td>
												</tr>
												<tr>
													<th>제목</th>
													<td><input type="text" id="inquirySubject"
														class="form-control"></td>
												</tr>
												<tr>
													<th>내용</th>
													<td><textarea title="내용" id="inquiryContent"
															class="form-control"></textarea></td>
												</tr>
											</tbody>
										</table>
										<div>
											<input type="checkbox" id="checkSecret" class="form-control">
											<label for="checkSecret">비밀글로 문의하기</label>
										</div>
									</div>
								</div>
								<div class="py-3 border-bottom text-center">
									<button type="button" class="btn btn-primary"
										id="inquirySaveBtn" data-dismiss="modal">확인</button>
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">취소</button>
								</div>
							</div>
						</div>
					</div>
					<!-- 문의하기 모달 끝 -->
				</div>
			</div>
			<!-- 상품 문의 끝 -->

			<!-- 교환/반품안내 -->
			<div id="tabChange" class="text-center product-tab d-none">
				<h1>교환 반품 안내 사항</h1>
				<h1>교환 반품 안내 사항</h1>
				<h1>교환 반품 안내 사항</h1>
				<h1>교환 반품 안내 사항</h1>
				<h1>교환 반품 안내 사항</h1>
			</div>
		</div>
	</div>
	<!-- 상품 상세 정보 끝 -->
</div>



<script>
	$(document).ready(function() {

		/* 메뉴 고정 */
		let Offset = $('.product-menu-detail').offset();
		$(window).scroll(function() {
			if ($(document).scrollTop() > Offset.top) {
				$('.product-menu-detail').addClass('fixed');
			} else {
				$('.product-menu-detail').removeClass('fixed');
			}
		});

		/* 장바구니 추가 */
		$(".add-cart").on("click", function() {
			let productId = $(this).data("product-id");
			let count = $("#count").val();

			if (count < 1) {
				alert("수량을 선택해주세요");
				return;
			}

			$.ajax({
				type : "POST",
				url : "/cart/add",
				data : {
					"productId" : productId,
					"count" : count
				}

				, success : function(data) {
					alert("장바구니 추가 완료");
					location.reload(true);
				}
				, error : function(request, status, error) {
					alert("장바구니 추가 실패");
				}
			});
		});

	    /* 수량 버튼 */
		$(".plus-btn, .minus-btn").on("click", function() {
			let currentCount = parseInt($(this).siblings(".count").val());

			// 클릭된 버튼에 따라 수량 조절
			currentCount += ($(this).hasClass("plus-btn") ? 1 : -1);

			// 수량이 1 미만이 되지 않도록 보장
			currentCount = Math.max(currentCount, 1);

			// 결과를 count input에 설정
			$(this).siblings(".count").val(currentCount); 
		});

		/* 초기화 */
		$(".product-inquiry-btn").on("click", function() {
			$("#ic1").prop("checked", true);
			$("#inquirySubject").val("");
			$("#inquiryContent").val("");
			$("#checkSecret").prop("checked", false);
		});

		/* 문의하기 저장 */
		$("#inquirySaveBtn").on("click", function() {
				let productId = $("#addCartBtn").data("product-id");
				let inquiryCategory = $("input:radio[name='inquiryCategory']:checked").val();
				let inquirySubject = $("#inquirySubject").val().trim();
				let inquiryContent = $("#inquiryContent").val().trim();
				let inquirySecret = 2;
				if ($("#checkSecret").is(":checked")) {
					let inquirySecret = 1;
				}

				console.log(productId);
				console.log(inquiryCategory);
				console.log(inquirySubject);
				console.log(inquiryContent);
				console.log(inquirySecret);

				$.ajax({
					type : "post",
					url : "/inquiry/add",
					data : {
						"productId" : productId,
						"inquiryCategory" : inquiryCategory,
						"inquirySubject" : inquirySubject,
						"inquiryContent" : inquiryContent,
						"inquirySecret" : inquirySecret}

						, success : function(data) {
							alert("문의하기 성공");
							location.reload(true);
						}
						, error : function(request, status, error) {
							alert("문의하기 실패");
						}
				});

			});

			$(".favorite-button").on("click", function() {
				if ($(this).hasClass("button-favorite")) {
					$(this).removeClass("button-favorite");
					$(this).addClass("button-favorite-active");
					return;
				} else {
					$(this).removeClass("button-favorite-active");
					$(this).addClass("button-favorite");
				}
			});

			$(".board-pagenation").children().children().children().on("click", function() {
				$(".board-pagenation").find(".on").removeClass("on");
				$(this).parent().addClass("on");
			});
			
			$(".product-menu-tab").on("click", function() {
				let tabName = $(this).children().attr("href").substring(1);
				$(".product-tab").addClass("d-none");
				$("#" + tabName).removeClass("d-none");
				
				
			});
			
/* 			$("#paginationContainer").on("click", ".page-link", function (e) {
		        e.preventDefault();
		        
		        let pageNo = $(this).data("page-no");
		        
		        $.ajax({
		            type: "GET"
		            , url: "/product/" + "${product.product.id}/"
		            , data: {page: pageNo - 1}
		            , success: function (data) {
		                // 반환된 데이터를 사용하여 페이징 부분 업데이트
		                $(".current-page").html(data.productListFragment);
		            }
		            , error: function (request, status, error) {
		                alert("error");
		            }
		        });
		    }); */

		});
</script>