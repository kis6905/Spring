package com.leaf.server.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.service.SampleService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class SampleHandler {
	
	@Autowired
	private SampleService sampleService;

	public Mono<ServerResponse> findAll(ServerRequest request) {
		
		log.info("-> []");
		
		// TODO: Flux로 어떻게 변경해주지?
		return Mono.fromCompletionStage(sampleService.findAll())
				.flatMap(member -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(member)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> findByOne(ServerRequest request) {
		final Long id = Long.valueOf(request.pathVariable("id"));
		
		log.info("-> [id = {}]", id);
		
		CompletableFuture<Member> completableFuture = sampleService.findByOne(id);
		
		log.info("~~ [end]");
		
		return Mono.fromCompletionStage(completableFuture)
				.flatMap(member -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(member)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		Mono<Member> memberMono = request.bodyToMono(Member.class);
		
		// TODO
		Member member = memberMono.block();
		
		log.info("-> [member = {}]", member);
		
		return Mono.fromCompletionStage(sampleService.add(memberMono.block()))
				.flatMap(Member -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(Member)));
	}

	public Mono<ServerResponse> modify(ServerRequest request) {
		Mono<Member> memberMono = request.bodyToMono(Member.class);
		
		// TODO
		Member member = memberMono.block();
		
		log.info("-> [member = {}]", member);
		
		return Mono.fromCompletionStage(sampleService.modify(member))
				.flatMap(Member -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(Member)));
	}

	public Mono<ServerResponse> remove(ServerRequest request) {
		final Long id = Long.valueOf(request.pathVariable("id"));
		
		log.info("-> [id = {}]", id);
		
		sampleService.remove(id);
		return ServerResponse.ok().build();
	}
	
}
