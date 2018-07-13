package com.leaf.server.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.server.common.response.ApiResponse;
import com.leaf.server.common.response.ApiResponse.Result;
import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.service.SampleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * RESETful API and GraphQL Samples.
 * 
 * @author iskwon
 * @since 2018. 6. 18.
 */
@RestController
@RequestMapping(value = "/sample")
@Api(tags = { "Sample APIs" })
@Slf4j
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	/**
	 * RESTful API Sample
	 */
	@ApiOperation(value = "[REST] GET list sample")
	@GetMapping(value = "/members")
	public ResponseEntity<ApiResponse> getMembers() throws Exception {
		List<Member> list = sampleService.getMembers();
		return new ResponseEntity<ApiResponse>(new ApiResponse(Result.OK, list), HttpStatus.OK);
	}
	
	@ApiOperation(value = "[REST] GET sample")
	@GetMapping(value = "/member/{username}")
	public ResponseEntity<ApiResponse> getMember(@PathVariable String username) throws Exception {
		Member member = sampleService.getMember(username);
		return new ResponseEntity<ApiResponse>(new ApiResponse(Result.OK, member), HttpStatus.OK);
	}
	
	@ApiOperation(value = "[REST] POST sample")
	@PostMapping(value = "/member")
	public ResponseEntity<ApiResponse> postMember(@RequestBody Member member) throws Exception {
		sampleService.addMember(member);
		return new ResponseEntity<ApiResponse>(new ApiResponse(Result.OK), HttpStatus.OK);
	}
	
	@ApiOperation(value = "[REST] PUT sample")
	@PutMapping(value = "/member")
	public ResponseEntity<ApiResponse> putMember(@RequestBody Member member) throws Exception {
		sampleService.modifyMember(member);
		return new ResponseEntity<ApiResponse>(new ApiResponse(Result.OK), HttpStatus.OK);
	}
	
	@ApiOperation(value = "[REST] DELETE sample")
	@DeleteMapping(value = "/member")
	public ResponseEntity<ApiResponse> deleteMember(@RequestBody Member member) throws Exception {
		sampleService.removeMember(member);
		return new ResponseEntity<ApiResponse>(new ApiResponse(Result.OK), HttpStatus.OK);
	}
	
	/**
	 * GraphQL Sample
	 */
	@ApiOperation(value = "[GraphQL] Query sample", notes = "Query Example\n {\nallMembers {\n username\n age\n phoneNumber\n role\n }\n }")
	@PostMapping(value = "/graphql", produces = "application/json")
	public ResponseEntity<Object> memberGraphql(@ApiParam(value = "This is graphql query", required = true)
												@RequestBody String body) {
		log.info("body = {}", body);
		return new ResponseEntity<Object>(sampleService.getGraphQL().execute(body), HttpStatus.OK);
	}
}
