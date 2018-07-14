package com.leaf.server.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfigruation {
	
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
    	/*
    	 * CorePool에 있는 Thread가 모두 사용중이면 QueueCapacity 만큼 Queue에 쌓고,
    	 * QueueCapacity도 모두 채워지면 MaxPoolSize 만큼 Thread를 추가로 생성한다.
    	 * MaxPoolSize만큼의 Thread도 사용중이면 reject 한다.
    	 */
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("my-executor-");
        taskExecutor.initialize();
        return taskExecutor;
    }
	
}
