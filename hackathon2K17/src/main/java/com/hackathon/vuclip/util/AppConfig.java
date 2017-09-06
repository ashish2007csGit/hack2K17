package com.hackathon.vuclip.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

	
	@Configuration
	@ComponentScan(basePackages = "com.hackathon.vuclip.util")
	public class AppConfig {

		@Bean
		public ThreadPoolTaskExecutor taskExecutor() {
			ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
			pool.setCorePoolSize(8);
			pool.setMaxPoolSize(15);
			pool.setWaitForTasksToCompleteOnShutdown(true);
			return pool;
		}

	}

