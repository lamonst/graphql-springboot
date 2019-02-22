package com.graphql.local.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.coxautodev.graphql.tools.SchemaParser;
import com.graphql.local.resolvers.BaseResolver;
import com.graphql.local.scalars.Scalars;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

public class AbstractController {

	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

	@Autowired
	private BaseResolver baseService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity getExecuteGraphQL(String query) {
		GraphQLSchema makeExecutableSchema = SchemaParser.newParser().file("schema.graphqls")
				.resolvers(baseService.getResolvers()).scalars(Scalars.dateTime).build().makeExecutableSchema();
		ExecutionResult executionResult = GraphQL.newGraphQL(makeExecutableSchema).build().execute(query);
		Map<String, Object> result = new LinkedHashMap<>();
		if (executionResult.getErrors().size() > 0) {
			result.put("errors", executionResult.getErrors());
			log.error("Errors: {}", executionResult.getErrors());
			return ResponseEntity.badRequest().body(result);
		}
		result.put("data", executionResult.getData());
		return ResponseEntity.ok(result);
	}

}
