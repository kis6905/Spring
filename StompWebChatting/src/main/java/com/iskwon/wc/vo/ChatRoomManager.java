package com.iskwon.wc.vo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class ChatRoomManager {
	
	private static ChatRoomManager chatRoomManager = new ChatRoomManager(); 
	
	public static ChatRoomManager getInstance() {
		return chatRoomManager;
	}
	
	private Integer chatRoomId		= null;
	private String chatRoomName		= null;
	private Hashtable<String, String> users = new Hashtable<String, String>();

	public Integer getChatRoomId() {
		return chatRoomId;
	}
	
	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	
	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}
	
	public List<String> getUserIdList() {
		List<String> userIdList = new ArrayList<String>();
		
		Enumeration<String> elements = users.elements();
		while (elements.hasMoreElements()) {
			String userId = (String) elements.nextElement();
			userIdList.add(userId);
		}
		
		return userIdList;
	}
	
	public void addUser(String sessionId, String userId) {
		if (!users.containsKey(sessionId))
			users.put(sessionId, userId);
	}
	
	public void removeUser(String sessionId) {
		if (users.containsKey(sessionId))
			users.remove(sessionId);
	}
	
}
