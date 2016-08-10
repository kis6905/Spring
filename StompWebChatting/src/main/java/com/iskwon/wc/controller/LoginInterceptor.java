package com.iskwon.wc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			logger.info("-> [session = null]");
			
			logger.info("<- [sendRedirect = login]");
			response.sendRedirect("/");
			return false;
		}
		else {
			String userId = (String) session.getAttribute("userId");
			if (userId == null || userId.isEmpty()) {
				logger.info("-> [userId = null]");
				
				logger.info("<- [sendRedirect = login]");
				response.sendRedirect("/");
				return false;
			}
		}
		
		return true;
	}
}
