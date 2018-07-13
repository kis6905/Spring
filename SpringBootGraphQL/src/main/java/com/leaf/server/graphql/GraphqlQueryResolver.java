//package com.leaf.server.graphql;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import com.leaf.server.domain.User;
//import com.leaf.server.repository.UserRepository;
//
//@Component
//public class GraphqlQueryResolver implements GraphQLQueryResolver {
//	
//	@Autowired
//	private UserRepository userRepository;
//
//	public List<User> allUsers() {
//		return userRepository.findAll();
//	}
//	
//	public User user(Long id) {
//		return userRepository.findById(id);
//	}
//	
//}
