package com.graphql.local.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.local.domain.Link;
import com.graphql.local.domain.User;
import com.graphql.local.repository.UserRepository;

/**
 * Contains Link sub-queries
 */
@Component
public class LinkResolver implements GraphQLResolver<Link> {

	@Autowired
	private UserRepository userRepository;

	public User postedBy(Link link) {
		if (link.getUserId() == null) {
			return null;
		}
		return userRepository.findById(link.getUserId());
	}
}
