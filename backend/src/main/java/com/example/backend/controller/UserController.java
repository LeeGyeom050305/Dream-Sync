package com.example.backend.controller;

import com.example.backend.annotation.CheckRole;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.UserCreateRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.enums.UserRoleType;
import com.example.backend.service.UserService;
import com.example.backend.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

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

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @GetMapping
    @CheckRole({UserRoleType.USER, UserRoleType.ADMIN, UserRoleType.SADMIN})
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userService.getUser(SecurityUtil.currentUserId()));
    }

    @GetMapping("/all")
    @CheckRole({UserRoleType.USER, UserRoleType.ADMIN, UserRoleType.SADMIN})
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}