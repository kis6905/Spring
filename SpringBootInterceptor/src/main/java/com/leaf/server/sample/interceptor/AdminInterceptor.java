package com.leaf.server.sample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Component("adminInterceptor")
@Slf4j
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.info("========== preHandle()");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		log.info("========== postHandle()");
		
		/*
		 * @ReseController의 response면 interceptor에서 header 추가가 안 된다.
		 * 대신 ResponseBodyAdvice를 구현한 @ControllerAdvice에서 추가해줄수 있다.
		 * 
		 * 참고 - https://stackoverflow.com/questions/48823794/spring-interceptor-doesnt-add-header-to-restcontroller-services
		 */
		response.addHeader("Post-Handle", "interceptor");
		response.getHeaderNames()
				.forEach(name -> log.info(">> {}: {}", name, response.getHeader(name)));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.info("========== afterCompletion()");
	}

}
