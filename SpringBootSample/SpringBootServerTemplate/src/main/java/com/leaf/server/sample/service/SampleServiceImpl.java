package com.leaf.server.sample.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.leaf.server.sample.dto.Member;
import com.leaf.server.sample.repository.MemberRepository;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author iskwon
 * @since 2018. 6. 18.
 */
@Service
@Slf4j
public class SampleServiceImpl implements SampleService {
	
    @Value("classpath:graphql/sample.graphql")
    Resource schemaResource;
    
    @Autowired
    private DataFetcher<List<Member>> allMembersDataFetcher;
    
    @Autowired
    private DataFetcher<Member> memberDataFetcher;
    
    @Autowired
    private MemberRepository memberRepository;
    
    private  GraphQL graphQL;
    
    @PostConstruct
    public void postConstruct() throws IOException {
    	loadGraphQLSchema();
    }
    
    private void loadGraphQLSchema() throws IOException {
        File schema = schemaResource.getFile();
        
        log.info("==================================================================");
        log.info("load schema file: {}", schema.getName());
        log.info("==================================================================");
        
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);
        RuntimeWiring runtimeWiring = initWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }
    
    private RuntimeWiring initWiring() {
    	return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
								                .dataFetcher("allMembers", allMembersDataFetcher)
								                .dataFetcher("member", memberDataFetcher))
                .build();
    }
	
	@Override
	public GraphQL getGraphQL() {
		return graphQL;
	}

	@Override
	public List<Member> getMembers() throws Exception {
		return memberRepository.findAll();
	}

	@Override
	public Member getMember(String username) throws Exception {
		return memberRepository.findByName(username);
	}

	@Override
	public boolean addMember(Member member) throws Exception {
		memberRepository.add(member);
		return true;
	}

	@Override
	public boolean modifyMember(Member member) throws Exception {
		memberRepository.modify(member);
		return true;
	}

	@Override
	public boolean removeMember(Member member) throws Exception {
		memberRepository.remove(member);
		return false;
	}
	
}
