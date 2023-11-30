package com.withdog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect // 부가 기능 정의(advice) + 어디에 적용(pointcut) => 합친 것
@Component
public class TimeTraceAOP { // Singleton

	//@Around("execution(* com.withdog..*(..))") // 1. 패키지 범위 => 어디에 적용?(pointcut)
	@Around("@annotation(TimeTrace)") // 어느 어노테이션이 붙어 있을 때 사용할 것인가?
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// 타겟이 적용하는 메소드 - joinPoint (어느 메소드에 껴넣을 것?)
		
		// 시간 측정
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object proceedObj = joinPoint.proceed(); // 본래 할 일 수행(예: 회원가입)
		
		stopWatch.stop();
		log.info("$$$$$$$$$$$$$$ 실행 시간(ms): " + stopWatch.getTotalTimeMillis());
		log.info(stopWatch.prettyPrint());
		
		return proceedObj;
	}
}
