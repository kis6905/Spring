package com.iskwon.wc.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class SessionManager {
	
	private final static SessionManager sessionManager = new SessionManager();
	
	public static SessionManager getInstance() {
		return sessionManager;
	}
	
	private List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	public void add(WebSocketSession session) {
		list.add(session);
	}
	
	public List<WebSocketSession> getList() {
		return list;
	}
	
	public void remove(WebSocketSession session) {
		list.remove(session);
	}
	
}
