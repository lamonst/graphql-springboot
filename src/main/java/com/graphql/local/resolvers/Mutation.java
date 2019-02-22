package com.graphql.local.resolvers;

import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.graphql.local.domain.AuthData;
import com.graphql.local.domain.Link;
import com.graphql.local.domain.SigninPayload;
import com.graphql.local.domain.User;
import com.graphql.local.domain.Vote;
import com.graphql.local.repository.LinkRepository;
import com.graphql.local.repository.UserRepository;
import com.graphql.local.repository.VoteRepository;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

/**
 * Mutation root
 */
@Component
public class Mutation implements GraphQLRootResolver {
    
	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VoteRepository voteRepository;
    
    
    public Link createLink(String url, String description, DataFetchingEnvironment env) {
//        AuthContext context = env.getContext();
//        Link newLink = new Link(url, description, context.getUser().getId());
        Link newLink = new Link(url, description, null);
        return linkRepository.saveLink(newLink);
//        return newLink;
    }
    
    public User createUser(String name, AuthData auth) {
        User newUser = new User(name, auth.getEmail(), auth.getPassword());
        return userRepository.saveUser(newUser);
    }

    public SigninPayload signinUser(AuthData auth) {
        User user = userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }
    
    public Vote createVote(String linkId, String userId) {
        return voteRepository.saveVote(new Vote(Instant.now().atZone(ZoneOffset.UTC), userId, linkId));
    }
}
