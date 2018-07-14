package com.leaf.server.sample.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SampleService {
	
	@Async("threadPoolTaskExecutor")
	public CompletableFuture<String> asyncTask(String id) {
		log.info("~~ start asyncTask [id = {}]", id);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		log.info("~~ end asyncTask");
		return CompletableFuture.completedFuture("hello " + id);
	}
	
}
