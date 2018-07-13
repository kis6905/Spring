package com.leaf.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.server.service.GraphqlService;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GraphqlController {
	
	@Autowired
	private GraphqlService graphqlService;
	
	@PostMapping(value = "/graphql")
	public ResponseEntity<Object> graphql(@RequestBody String body) {
		
		log.info("-> [body = {}]", body);
		
		ExecutionResult result = graphqlService.getGraphQL().execute(body);
		Object resultData = result.getData();
		
		if (resultData == null) {
			for (GraphQLError error : result.getErrors()) {
				log.error(error.toString());
			}
		}
		
		log.info("<- [resultData = {}]", resultData == null ? "null" : resultData);
		
		return ResponseEntity.ok(result);
	}
	
}
