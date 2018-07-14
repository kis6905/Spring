package com.leaf.server.sample.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.server.sample.service.SampleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping(value = "/hello/{id}")
	public CompletableFuture<String> hello(@PathVariable String id) {
		log.info("-> [id = {}]", id);
		return sampleService.asyncTask(id);
	}
	
}
