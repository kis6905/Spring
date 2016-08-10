package com.iskwon.wc.controller;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class WebsocketInterceptor extends HttpSessionHandshakeInterceptor {
	
//	private static final Logger logger = LoggerFactory.getLogger(WebsocketInterceptor.class);
	
	@Override
	public boolean beforeHandshake(
			ServerHttpRequest request,
	        ServerHttpResponse response, 
	        WebSocketHandler wsHandler,
	        Map<String, Object> attributes) throws Exception {
		
//		logger.info("-> []");
//		
//		if (request instanceof ServletServerHttpRequest) {
//			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//	        HttpSession session = servletRequest.getServletRequest().getSession(false);
	        
//	        if (session != null && !attributes.containsKey("userId")) {
//		    	String userId = (String) session.getAttribute("userId");
//		    	logger.info("~~ [Put userId = {}]", userId);
//		    	attributes.put("userId", userId);
//		    }
//		}
//		
//		logger.info("<- []");
		
	    return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
	@Override
	public void afterHandshake(
			ServerHttpRequest request,
			ServerHttpResponse response,
			WebSocketHandler wsHandler,
			Exception ex) {
		super.afterHandshake(request, response, wsHandler, ex);
	}
	
}
