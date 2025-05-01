package com.example.backend.annotation;

import com.example.backend.enums.UserRoleType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRole {
    UserRoleType[] value();
}