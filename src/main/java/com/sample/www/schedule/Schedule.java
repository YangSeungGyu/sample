package com.sample.www.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("JobSchedule")
public class Schedule {

	// 1분마다 SMS 모듈이 정상구동되었나 체크
	@Scheduled(cron = "0 * * * * *")
	public void test() throws Exception {
		System.out.println("스케줄러 테스트");
	}

}
