package com.iskwon.wc.vo;

public class UserMessage {
	
	private String userId;
	private String message;

	public String getUserId() {
		return userId;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "UserMessage [userId=" + userId + ", message=" + message + "]";
	}
	
}
