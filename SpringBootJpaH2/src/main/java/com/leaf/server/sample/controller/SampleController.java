package com.leaf.server.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.server.common.response.ApiResponse;
import com.leaf.server.common.response.ApiResponse.Result;
import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.service.SampleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping(value = "/members")
	public ResponseEntity<ApiResponse> getMembers() {
		log.info("-> []");
		List<Member> list = sampleService.getMembers();
		return ResponseEntity.ok(new ApiResponse(Result.OK, list));
	}
	
	@GetMapping(value = "/member/{memberId}")
	public ResponseEntity<ApiResponse> getMember(@PathVariable(name = "memberId") String memberId) {
		log.info("-> [memberId = {}]", memberId);
		Member member = sampleService.getMember(memberId);
		
		Result result = Result.OK;
		if (member == null) {
			result = Result.NOK;
		}
		
		return ResponseEntity.ok(new ApiResponse(result, member));
	}
	
	@PostMapping(value = "/member")
	public ResponseEntity<ApiResponse> postMember(@RequestBody Member member) {
		log.info("-> [member = {}]", member.toString());
		
		List<Member> list = sampleService.insertMember(member);
		
		return ResponseEntity.ok(new ApiResponse(Result.OK, list));
	}
	
	@PutMapping(value = "/member")
	public ResponseEntity<ApiResponse> putMember(@RequestBody Member member) {
		log.info("-> [member = {}]", member.toString());
		
		List<Member> list = sampleService.updateMember(member);
	
		return ResponseEntity.ok(new ApiResponse(Result.OK, list));
	}
	
	@DeleteMapping(value = "/member")
	public ResponseEntity<ApiResponse> deleteMember(@RequestBody Member member) {
		log.info("-> [member = {}]", member.toString());
		
		List<Member> list = sampleService.deleteMember(member);
	
		return ResponseEntity.ok(new ApiResponse(Result.OK, list));
	}
	
}
