package com.develeaf.study.sample.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develeaf.study.sample.entity.UserEntity;
import com.develeaf.study.sample.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public List<UserEntity> getUserList() {
		return userRepository.findAll();
	}
	
	@Transactional
	public UserEntity getUserByName(String name) {
		UserEntity entity = userRepository.findByName(name);
		log.debug("entity: {}", entity);
		return entity;
	}
	
}
