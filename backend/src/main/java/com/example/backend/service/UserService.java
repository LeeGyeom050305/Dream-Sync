package com.example.backend.service;

import com.example.backend.dto.LoginRequest;
import com.example.backend.entity.UserEntity;
import com.example.backend.enums.ErrorCode;
import com.example.backend.exception.AppException;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthTokenProvider authTokenProvider;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public boolean isValidPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // 로그인 처리 메서드
    public String login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUserName(loginRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        CustomUserDetailsService.UserPrincipal principal = CustomUserDetailsService.UserPrincipal.of(user);
        if (!isValidPassword(loginRequest.getPassword(), principal.getPassword()))
            throw new AppException(ErrorCode.INVALID_PASSWORD);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal.getUsername(), loginRequest.getPassword(), principal.getAuthorities()));
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        return authTokenProvider.createToken(principal);
    }



}