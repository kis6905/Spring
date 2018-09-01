package com.leaf.server.sample.web;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SampleRouter {
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction(SampleHandler sampleHandler) {
		log.info("~~ routerFunction()");
		
		return route(GET("/v2/member/{id}").and(accept(MediaType.APPLICATION_JSON)), sampleHandler::findByOne)
				.andRoute(GET("/v2/members").and(accept(MediaType.APPLICATION_JSON)), sampleHandler::findAll)
				.andRoute(POST("/v2/member").and(accept(MediaType.APPLICATION_JSON)), sampleHandler::add)
				.andRoute(PUT("/v2/member").and(accept(MediaType.APPLICATION_JSON)), sampleHandler::modify)
				.andRoute(DELETE("/v2/member/{id}").and(accept(MediaType.APPLICATION_JSON)), sampleHandler::remove);
	}
	
}
