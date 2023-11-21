package com.withdog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

@SpringBootTest // spring boot를 기동 (BO 테스트 등) 가볍게 볼 때는 어노테이션 없으면 순수한 자바
class WithDogApplicationTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	@Test
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
