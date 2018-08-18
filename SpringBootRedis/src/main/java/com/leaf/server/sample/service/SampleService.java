package com.leaf.server.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaf.server.sample.dto.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SampleService {
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public User getUser(int id) {
		
		String result = (String) redisTemplate.opsForValue().get(String.valueOf(id));
		
		log.info("~~ [result = {}]", result);
		
		User user = null;
		try {
			if (result != null)
				user = objectMapper.readValue(result, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
}
