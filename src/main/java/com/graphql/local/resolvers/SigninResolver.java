package com.graphql.local.resolvers;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.local.domain.SigninPayload;
import com.graphql.local.domain.User;

/**
 * Contains SigninPayload sub-queries
 */

@Component
public class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
