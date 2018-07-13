//package com.leaf.server.graphql;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import com.leaf.server.domain.User;
//import com.leaf.server.repository.UserRepository;
//
//@Component
//public class GraphqlMutationResolver implements GraphQLMutationResolver {
//	
//	@Autowired
//	private UserRepository userRepository;
//
//	public User addUser(String name, Integer age) {
//		User user = User.builder().name(name).age(age).build();
//		return userRepository.add(user);
//	}
//	
//}
