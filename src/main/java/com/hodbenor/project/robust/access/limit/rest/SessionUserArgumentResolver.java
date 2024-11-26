package com.hodbenor.project.robust.access.limit.rest;

import com.hodbenor.project.robust.access.limit.UserEndpointService;
import com.hodbenor.project.robust.access.limit.UserService;
import com.hodbenor.project.robust.access.limit.data.beans.UserEndpoint;
import com.hodbenor.project.robust.access.limit.data.beans.User;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.nio.file.AccessDeniedException;

@Component
public class SessionUserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOG = LoggerFactory.getLogger(SessionUserArgumentResolver.class);
    private static final int MAX_NUM_REQUEST = 5;
    private final UserService userService;
    private final UserEndpointService userEndpointService;

    public SessionUserArgumentResolver(UserService userService, UserEndpointService userEndpointService) {
        this.userService = userService;
        this.userEndpointService = userEndpointService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory)
            throws Exception {

        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String userId = request.getHeader("user-id");
        if (userId == null) {
            LOG.error("Missing user id");
            return ResponseEntity.noContent();
        }

        User user = userService.getUser(Long.parseLong(userId));
        if (user == null) {
            LOG.error("user equal null");
            return ResponseEntity.noContent();
        }

        RestValidator restValidator = parameter.getMethodAnnotation(RestValidator.class);
        if (restValidator == null) {
            return user;
        }

        long endpointId = restValidator.endpointId();

        //TODO lock user
        UserEndpoint userEndpoint = userEndpointService.getUserEndpoint(user.getId(), endpointId)
                .orElseGet(() -> {
                    UserEndpoint newUserEndpoint = new UserEndpoint();
                    newUserEndpoint.setUserId(user.getId());
                    newUserEndpoint.setEndpointId(endpointId);
                    newUserEndpoint.setNumRequests(0);
                    return newUserEndpoint;
                });

        if (userEndpoint.getNumRequests() >= MAX_NUM_REQUEST) {
            throw new AccessDeniedException(String.format("User %s has reached the maximum number of requests allowed", userId));
        }

        if (userEndpoint.getNumRequests() == 0) {
            userEndpoint.setNumRequests(1);
            userEndpointService.createUserEndpoint(userEndpoint);
        } else {
            userEndpointService.increment(userEndpoint.getId(), 1);
        }
        //TODO unlock user

        return user;
    }
}