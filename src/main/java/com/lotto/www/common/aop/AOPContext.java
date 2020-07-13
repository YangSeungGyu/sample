package com.lotto.www.common.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
public class AOPContext {

	@Bean(name = "logAOP")
	public LogAOP getLogAOP() {
		return new LogAOP();
	}

}
