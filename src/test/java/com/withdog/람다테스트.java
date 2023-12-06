package com.withdog;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class 람다테스트 {

	//@Test
	void 람다테스트1() {
		List<String> list = List.of("apple", "banana", "grape");
		
		// stream
		list
		.stream()	// Stream 객체 / 계속 줄줄이 이어지다
		.filter(e -> e.startsWith("b")) // 조건문
		.forEach(e -> log.info("$$$$$$$$$$$$ {}", e)); // 반복문
	}
	
	//@Test
	void 람다테스트2() {
		List<String> list = List.of("apple", "banana", "grape");
		
		list = list // list 업데이트
		.stream()
		.map(e -> e.toUpperCase()) // stream
		.collect(Collectors.toList()); // stream to list
		
		log.info("$$$$$ {}", list);
	}
	
	@Test
	void 메소드레퍼런스() {
		List<String> list = List.of("apple", "banana", "grape");
		list = list
		.stream()
		.map(String::toUpperCase) // e -> e.toUpperCase()
		.collect(Collectors.toList());
		
		log.info("$$$$$ {}", list);
	}
}
