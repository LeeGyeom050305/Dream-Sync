package com.example.backend.controller;

import com.example.backend.dto.LoginRequest;
import com.example.backend.entity.UserEntity;
import com.example.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 로그인 API
    @Operation(summary = "login", description = "User Login (Get Access Token)")
    @PostMapping("/login")
    //@CheckRole({UserRoleType.USER, UserRoleType.ADMIN, UserRoleType.SADMIN})
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty())
            throw new Exception("username is required");
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty())
            throw new Exception("password is required");

        return ResponseEntity.ok(userService.login(loginRequest));
    }
}