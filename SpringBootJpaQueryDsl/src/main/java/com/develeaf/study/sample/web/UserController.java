package com.develeaf.study.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.develeaf.study.sample.entity.TeamEntity;
import com.develeaf.study.sample.entity.UserEntity;
import com.develeaf.study.sample.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<UserEntity> getUsers() {
		List<UserEntity> list = userService.getUserList();
		return list;
	}
	
	@GetMapping("/user/{name}")
	public UserEntity getUser(@PathVariable(value = "name") String name) {
		log.debug("name: {}", name);
		UserEntity entity = userService.getUserByName(name);
		log.debug("entity: {}", entity.toString());
		return entity;
	}
	
	@GetMapping("/teams")
	public List<TeamEntity> getTeams() {
		List<TeamEntity> list = userService.getTeamList();
		return list;
	}
	
}
