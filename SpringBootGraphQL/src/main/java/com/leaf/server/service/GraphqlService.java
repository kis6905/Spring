package com.leaf.server.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.leaf.server.domain.User;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GraphqlService {
	
    @Value("classpath:user.graphqls")
    org.springframework.core.io.Resource schemaResource;
	
	@Autowired
	private DataFetcher<List<User>> allUserDatafetcher;
	
	@Autowired
	@Qualifier("userDataFetcher")
	private DataFetcher<User> userDataFetcher;
	
	@Autowired
	@Qualifier("addUserDataFetcher")
	private DataFetcher<User> addUserDataFetcher;
	
	@Autowired
	@Qualifier("modifyUserDataFetcher")
	private DataFetcher<User> modifyUserDataFetcher;
	
	@Autowired
	@Qualifier("removeUserDataFetcher")
	private DataFetcher<User> removeUserDataFetcher;
	
    private GraphQL graphQL;
    
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
	                .dataFetcher("allUsers", allUserDatafetcher)
	                .dataFetcher("user", userDataFetcher))
                .type("Mutation", typeWiring -> typeWiring
	                .dataFetcher("addUser", addUserDataFetcher)
	                .dataFetcher("modifyUser", modifyUserDataFetcher)
					.dataFetcher("removeUser", removeUserDataFetcher))
                .build();
    }
    
    public GraphQL getGraphQL() {
    	return graphQL;
    }
    
}
