package com.sample.www.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
	@Scheduled(cron = "0 * * * * *")
	public void test() throws Exception {
		System.out.println("스케줄러 테스트");
	}

}
