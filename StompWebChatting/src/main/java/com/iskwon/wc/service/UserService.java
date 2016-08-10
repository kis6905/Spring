package com.iskwon.wc.service;

import org.springframework.stereotype.Service;

import com.iskwon.wc.vo.User;

@Service
public class UserService {
	
	/**
	 * User 리턴
	 */
	public User getUser(String userId) {
		// TODO DB에서 긁어와야 함
		User user = new User();
		user.setUserId(userId);
		
		return user;
	}
	
}
