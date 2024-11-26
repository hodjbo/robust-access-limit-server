package com.hodbenor.project.robust.access.limit.rest;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RestValidator {
    long endpointId();
}
