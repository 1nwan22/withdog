<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="rightSideWrap" class="right-side-wrap p-3 w-100">
    <!-- 담은 상품들 -->
    <div>
        <div id="cartItems"></div>
    </div>
    
    <hr>
    <!-- 버튼 박스 -->
    <div id="totalPrice">
        
    </div>
    <div>
        <a href="/cart" id="cartBtn" class="btn btn-light form-control">장바구니</a>
        <button type="button" id="checkoutBtn" class="btn form-control">구매하기</button>
    </div>
</div>

<script>
    $(document).ready(function() {
        let currentPosition = parseInt($(".right-side-wrap").css("top"));
        $(window).scroll(function() {
            let position = $(window).scrollTop(); 
            $("#rightSideWrap").stop().animate({"top":position+currentPosition+"px"},500);
        });
        
        let cartCookie = getCookie("Cart");
		let totalPrice = 0;

        if (cartCookie) {
            let products = cartCookie.split("|");
            
            for (let i = 0; i < products.length; i++) {
                // 값이 없는 경우 continue
                if (!products[i]) {
                    continue;
                }

                let productInfo = products[i].split(":");
                let productBrand = productInfo[1];
                let productName = productInfo[2];
                let price = productInfo[3];
                let count = productInfo[4];
                totalPrice += price * count;

                let itemHtml = '<div class="cart-box my-2">' +
                                    '<div class="d-flex justify-content-between">' +
                                        '<div>' + productBrand + '</div>' +
                                        '<div>' + productName + '</div>' +
                                        '<div>X</div>' +
                                    '</div>' +
                                    '<hr>' +
                                    '<div>' +
                                        '<button type="button" class="minus-btn">-</button>' +
                                        '<input type="text" class="count" value="' + count + '">' +
                                        '<button type="button" class="plus-btn">+</button>' +
                                    '</div>' +
                                    '<div>' +
                                        '<div>' + price + '</div>' +
                                    '</div>' +
                                '</div>';

                $("#cartItems").append(itemHtml);
            }
        } else {
            $("#cartItems").html("장바구니가 비어있음");
        }
        $("#totalPrice").append('<div class="my-2">총 금액: ' + totalPrice + '원</div>');

        // 쿠키 가져오기 함수
        function getCookie(name) {
		    let cookies = document.cookie.split(';');
		    for (let i = 0; i < cookies.length; i++) {
		        let cookie = cookies[i].trim();
		        if (cookie.startsWith(name + '=')) {
		            return cookie.substring(name.length + 1);
		        }
		    }
		    return null;
		}
        
        $("#order-btn").on("click", function() {
        	
        });
    });
</script>
