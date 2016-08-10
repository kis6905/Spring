package com.iskwon.wc.vo;

import java.util.List;

public class Chat {
	
	private String userId;
	private String content;
	private List<String> userIdList;

	public Chat() {
	}
	
	public Chat(String userId, String content, List<String> userIdList) {
		this.userId = userId;
		this.content = content;
		this.userIdList = userIdList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}
	
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	@Override
	public String toString() {
		return "Chat [userId=" + userId
				+ ", content=" + content
				+ ", userIdListCount=" + userIdList.size()
				+ "]";
	}
	
}
