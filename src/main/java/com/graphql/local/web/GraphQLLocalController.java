package com.graphql.local.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class GraphQLLocalController extends AbstractController {
	
	private static final Logger log = LoggerFactory.getLogger(GraphQLLocalController.class);
 

	@RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity executeOperation(@RequestBody Map body) {

        String query = (String) body.get("query");
        return getExecuteGraphQL(query);
    }
	
	
//	private static GraphQLSchema buildSchema() {
//        return SchemaParser.newParser()
//                .file("schema.graphqls")
//                .resolvers(
//                        new Query(linkRepository, voteRepository),
//                        new Mutation(linkRepository, userRepository, voteRepository),
//                        new SigninResolver(),
//                        new LinkResolver(userRepository),
//                        new VoteResolver(linkRepository, userRepository))
//                .scalars(Scalars.dateTime)
//                .build()
//                .makeExecutableSchema();
//    }
}
