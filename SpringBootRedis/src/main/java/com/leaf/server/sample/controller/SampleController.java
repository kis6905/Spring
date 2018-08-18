package com.leaf.server.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.server.sample.dto.User;
import com.leaf.server.sample.service.SampleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping(value = "/user/{id}")
	public User getUser(@PathVariable(value = "id") int id) {
		log.info("-> [id = {}]", id);
		
		return sampleService.getUser(id);
	}
	
}
