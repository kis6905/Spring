package com.leaf.server.sample.service;

import java.util.List;

import com.leaf.server.sample.dto.Member;

import graphql.GraphQL;

/**
 * 
 * @author iskwon
 * @since 2018. 6. 18.
 */
public interface SampleService {
	
	public GraphQL getGraphQL();
	
	public List<Member> getMembers() throws Exception;
	public Member getMember(String username) throws Exception;
	public boolean addMember(Member member) throws Exception;
	public boolean modifyMember(Member member) throws Exception;
	public boolean removeMember(Member member) throws Exception;
	
}
