<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<!--  시작 -->
	<div class="home-boxes d-flex flex-wrap" data-page-size="${postList.size}">
		<c:forEach items="${postList.content}" var="post">
		<div class="home-box my-2 p-0 m-0">
			<a href="/post/${post.id}" data-post-id="${post.id}" ">
				<img src="${post.imagePath}" class="post-image" alt="썸네일">
			</a>
		</div>
		</c:forEach>
	</div>
	<!--  끝 -->
</div>
<script>
$(document).ready(function() {


	$(window).on('scroll', onScroll);
    function onScroll() {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 50) {
            let lastPostId = $(".home-boxes").children().last().children().data("post-id");
            let pageSize = $(".home-boxes").data("page-size");
            console.log("lastPostId" + lastPostId);
            console.log(pageSize);
            $.ajax({
                type: "GET",
                url: "/post/load",
                success: function (data) {
                    let postList = data.postSlice.content;

                    for (let i = 0; i < postList.length; i++) {
                        let post = postList[i];
                        let postHtml = '<div class="home-box my-3">' +
                            '<a href="/post/' + post.id + '" data-post-id="' + post.id + '">' +
                            '<img src="' + post.imagePath + '" alt="썸네일" class="post-image">' +
                            '</a>' +
                            '</div>';
                        $('.home-boxes').append(postHtml);
                    }

                    if (data.postSlice.hasNext == false) {
                        $(window).off('scroll', onScroll);
                    }
                },
                error: function (request, status, error) {
                    console.log("로드 실패");
                }
            });
        }
    }
    
        
    
});
</script>
