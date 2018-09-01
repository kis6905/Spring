package com.leaf.server.sample.web;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.service.SampleService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("member")
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	@GetMapping("list")
	public Mono<List<Member>> findAll() {
		log.info("-> []");
		return Mono.fromCompletionStage(sampleService.findAll());
	}
	
	@GetMapping("{id}")
	public Mono<Member> findByOne(@PathVariable Long id) {
		log.info("-> [id = {}]", id);
		
		CompletableFuture<Member> completableFuture = sampleService.findByOne(id);
		
		log.info("~~ [end]");
		return Mono.fromCompletionStage(completableFuture);
	}
	
	@PostMapping
	public Mono<Member> add(@RequestBody Member Member) {
		log.info("-> []");
		return Mono.fromCompletionStage(sampleService.add(Member));
	}
	
	@PutMapping
	public Mono<Member> modify(@RequestBody Member Member) {
		log.info("-> []");
		
		CompletableFuture<Member> completableFuture = sampleService.modify(Member);
		
		log.info("~~ [end]");
		return Mono.fromCompletionStage(completableFuture);
	}
	
	@DeleteMapping("{id}")
	public void remove(@PathVariable Long id) {
		log.info("-> []");
		sampleService.remove(id);
	}
	
}
