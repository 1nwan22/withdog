package com.withdog.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestTaskJob {
	
	@Scheduled(cron = "1 * * * * *")
	public void test() {
		// Job 내용
		log.info("########## test job 수행");
	}


}
