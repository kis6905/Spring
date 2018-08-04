package com.leaf.server.sample.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleController {
	
	@GetMapping(value = "/member/{id}")
	public String getMember(@PathVariable String id) {
		log.info("-> [id = {}]", id);
		return "member" + id;
	}
	
	@GetMapping(value = "/admin/member/{id}")
	public String getAdminMember(@PathVariable String id, HttpServletResponse response) {
		log.info("-> [id = {}]", id);
		response.addHeader("My-Header", "test");
		return "admin member" + id;
	}
	
}
