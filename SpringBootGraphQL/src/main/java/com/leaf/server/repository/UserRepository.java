package com.leaf.server.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.leaf.server.domain.User;

@Repository
public class UserRepository {
	
	private List<User> userList;
	
	@PostConstruct
	public void postConstruct() {
		userList = new ArrayList<>();
		userList.add(User.builder().id(1L).name("iskwon").age(30).build());
		userList.add(User.builder().id(2L).name("hong").age(25).build());
		userList.add(User.builder().id(3L).name("kim").age(20).build());
	}
	
	public List<User> findAll() {
		return userList;
	}
	
	public User findById(long id) {
		return userList.stream()
				.filter(user -> id == user.getId())
				.findFirst()
				.orElse(null);
	}
	
	public User add(User user) {
		Long id = userList.stream()
						.map(u -> u.getId())
						.max((a, b) -> a.compareTo(b))
						.orElse(0L);
		user.setId(++id);
		userList.add(user);
		return user;
	}
	
	public User modify(User user) {
		User storedUser = findById(user.getId());
		storedUser.setAge(user.getAge());
		storedUser.setName(user.getName());
		return user;
	}
	
	public User remove(Long id) {
		User user = userList.stream()
						.filter(u -> id == u.getId())
						.findFirst()
						.orElse(null);
		userList.remove(user);
		return user;
	}
}
