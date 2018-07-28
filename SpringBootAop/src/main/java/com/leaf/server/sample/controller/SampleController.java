package com.leaf.server.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.service.SampleService;

@RestController
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping(value = "/list")
	public ResponseEntity<Object> getList(@RequestParam Integer count,
										  @RequestParam(required = false, defaultValue = "0") String test) {
		return ResponseEntity.ok(sampleService.getList(count));
	}
	
	@GetMapping(value = "/one/{id}")
	public ResponseEntity<Object> getOne(@PathVariable Integer id) {
		return ResponseEntity.ok(sampleService.getOne(id));
	}
	
	@PostMapping(value = "/member")
	public ResponseEntity<Object> postMember(@RequestBody Member member) {
		return ResponseEntity.ok(sampleService.insertMember(member));
	}
	
}
