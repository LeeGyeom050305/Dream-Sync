package com.example.backend.service;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.UserCreateRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.entity.UserEntity;
import com.example.backend.enums.ErrorCode;
import com.example.backend.enums.UserRoleType;
import com.example.backend.exception.AppException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principal.getUserId(), loginRequest.getPassword(), principal.getAuthorities()));
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        return authTokenProvider.createToken(principal);
    }

    public String signUp(UserCreateRequest request) {
        // 이메일 중복 확인
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 사용자 이름 중복 확인 (선택 사항)
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new AppException(ErrorCode.DUPLICATE_USERNAME);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 사용자 생성
        UserEntity user = UserEntity.builder()
                .userName(request.getUserName())
                .password(encodedPassword)
                .email(request.getEmail())
                .roleType(UserRoleType.USER) // 기본 역할 설정
                .deleteYn("N")
                .build();

        // DB에 저장
        userRepository.save(user);

        return "User registration successful";
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(int userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return UserMapper.INSTANCE.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        UserResponse responseDto = UserMapper.INSTANCE.toUserResponse(user);
        return responseDto;
    }



}