package com.withdog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import lombok.Getter;

@SpringBootTest // spring boot를 기동 (BO 테스트 등) 가볍게 볼 때는 어노테이션 없으면 순수한 자바
class WithDogApplicationTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Getter
	public enum Status { // domain이나 entity 패키지에 넣기 controller에서 enum으로 변환하고 DB까지 
		// jpa와 mybatis enum(handler or convert) 사용법이 다름
		// 열거형 정의
		Y(1, true), // 생성자 호출 ()값을 넣으면 생성자 구현해야함
		N(0, false); // 생성자 호출
		
		// enum 항목에 정의된 필드
		private int value1;
		private boolean value2;
		
		// 생성자: 필드에 값을 세팅
		Status(int value1, boolean value2) {
			this.value1 = value1;
			this.value2 = value2;
		}
		
	}
	
	@Test
	void someMethod(HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            System.out.println("Cookie Name: " + cookie.getName());
	            System.out.println("Cookie Value: " + cookie.getValue());
	        }
	    } else {
	        System.out.println("No cookies found.");
	    }
	}
	
	//@Test
	void enum_test1() {
		// given - 준비
		Status status = Status.Y;
		
		// when - 실행
		int value1 = status.getValue1();
		boolean value2 = status.isValue2(); // boolean은 getter is로 시작
		
		// then - 검증
		assertEquals(status, Status.Y);
		assertEquals(value1, 1);
		assertEquals(value2, true);
	}
	
	//@Test
	void contextLoads() {
	}
	
	//@Test
	void 더하기테스트() {
		logger.debug("######## 더하기 테스트 수행 ###########");
		int a = 10;
		int b = 20;
		assertEquals(31, a + b);
	}
	
	//@Test
	void checkTest() {
//		String str = null;
		String str = "";
		if (ObjectUtils.isEmpty(str)) { // null or "" 
			logger.info("########### str은 null 또는 비어있다. ###############");
		}
		
//		List<Integer> list = null;
		List<Integer> list = new ArrayList<>();
		if (ObjectUtils.isEmpty(list)) { // null or ""
			logger.info("$$$$$$$$$$$$$$$$$ list는 null이거나 비어있다. $$$$$$$$$$$$$$$$$");
		}
	}

}
