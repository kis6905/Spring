package com.leaf.server.sample.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.repository.MemberRepository;

import graphql.schema.DataFetcher;

/**
 * Actual graphQL query implements.
 * 
 * @author iskwon
 * @since 2018. 6. 18.
 */
@Configuration
public class DataFetcherFactory {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Bean
	public DataFetcher<List<Member>> allMembersDataFetcher() {
		return evn -> memberRepository.findAll();
	}
	
	@Bean
	public DataFetcher<Member> memberDataFetcher() {
		return evn -> memberRepository.findByName(evn.getArgument("username"));
	}
	
}
