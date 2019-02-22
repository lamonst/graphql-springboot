package com.graphql.local.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.local.domain.Link;
import com.graphql.local.domain.User;
import com.graphql.local.domain.Vote;
import com.graphql.local.repository.LinkRepository;
import com.graphql.local.repository.UserRepository;

/**
 * Contains vote sub-queries
 */
@Component
public class VoteResolver implements GraphQLResolver<Vote> {

	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private UserRepository userRepository;

	public User user(Vote vote) {
		return userRepository.findById(vote.getUserId());
	}

	public Link link(Vote vote) {
		return linkRepository.findById(vote.getLinkId());
	}
}
