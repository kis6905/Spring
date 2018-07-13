package com.leaf.server.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leaf.server.domain.User;
import com.leaf.server.repository.UserRepository;

import graphql.schema.DataFetcher;

@Configuration
public class DataFetcherConfiguration {
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean(name = "allUserDataFetcher")
	public DataFetcher<List<User>> allUserDataFetcher() {
		return evn -> userRepository.findAll();
	}
	
	@Bean(name = "userDataFetcher")
	public DataFetcher<User> userDataFetcher() {
		return evn -> userRepository.findById(evn.getArgument("id"));
	}
	
	@Bean(name = "addUserDataFetcher")
	public DataFetcher<User> addUserDataFetcher() {
		return evn -> userRepository.add(User.builder()
											.name(evn.getArgument("name"))
											.age(evn.getArgument("age")).build());
	}
	
	@Bean(name = "modifyUserDataFetcher")
	public DataFetcher<User> modifyUserDataFetcher() {
		return evn -> userRepository.modify(User.builder()
												.name(evn.getArgument("name"))
												.age(evn.getArgument("age")).build());
	}
	
	@Bean(name = "removeUserDataFetcher")
	public DataFetcher<User> removeUserDataFetcher() {
		return evn -> userRepository.remove(evn.getArgument("id"));
	}
	
}
