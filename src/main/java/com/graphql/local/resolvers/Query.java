package com.graphql.local.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.graphql.local.domain.Link;
import com.graphql.local.domain.Vote;
import com.graphql.local.filter.LinkFilter;
import com.graphql.local.repository.LinkRepository;
import com.graphql.local.repository.VoteRepository;

/**
 * Query root. Contains top-level queries.
 */
@Component
public class Query implements GraphQLRootResolver {

	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private VoteRepository voteRepository;

    public List<Link> allLinks(LinkFilter filter, Number skip, Number first) {
        return linkRepository.getAllLinks(filter, skip.intValue(), first.intValue());
    }
    
    public List<Vote> findByUserId(String userId) {
    	return voteRepository.findByUserId(userId);
    }
}
