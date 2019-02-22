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
	
//	private static final LinkRepository linkRepository;
//    private static final UserRepository userRepository;
//    private static final VoteRepository voteRepository;
//
//    static {//mongodb://graphql_db:graphql_db123@ds341825.mlab.com:41825/heroku_wd169ln1
////        MongoDatabase mongo = new MongoClient(new MongoClientURI("mongodb://graphql_db:graphql_db123@ds341825.mlab.com:41825")).getDatabase("heroku_wd169ln1");
//        MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
//        linkRepository = new LinkRepository(mongo.getCollection("links"));
//        userRepository = new UserRepository(mongo.getCollection("users"));
//        voteRepository = new VoteRepository(mongo.getCollection("votes"));
//    }
    

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
