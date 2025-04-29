package com.example.backend.controller;

import com.example.backend.entity.UserEntity;
import com.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 로그인 API
    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) {
        try {
            UserEntity user = userService.login(userName, password);
            return "로그인 성공! " + user.getUserName() + "님 환영합니다.";  // 로그인 성공 시 메시지 반환
        } catch (IllegalArgumentException e) {
            return e.getMessage();  // 로그인 실패 시 에러 메시지 반환
        }
    }
}