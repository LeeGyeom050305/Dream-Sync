package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private int userId;
    private String userName;
    private String roleType;
}