package com.example.backend.service;

import com.example.backend.entity.UserEntity;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 로그인 처리 메서드
    public UserEntity login(String userName, String password) {
        Optional<UserEntity> userOptional = userRepository.findByUserNameAndPassword(userName, password);
        if (userOptional.isPresent()) {
            return userOptional.get(); // 사용자가 존재하면 반환
        } else {
            throw new IllegalArgumentException("잘못된 아이디 또는 비밀번호입니다.");
        }
    }
}