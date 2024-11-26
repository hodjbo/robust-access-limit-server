package com.hodbenor.project.robust.access.limit.rest;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SessionUserResolverConfig implements WebMvcConfigurer {

    private final SessionUserArgumentResolver resolver;

    public SessionUserResolverConfig(SessionUserArgumentResolver resolver) {
        this.resolver = resolver;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(resolver);
    }
}

