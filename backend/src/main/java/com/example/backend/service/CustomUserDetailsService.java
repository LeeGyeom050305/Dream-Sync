package com.example.backend.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import com.example.backend.entity.UserEntity;
import com.example.backend.enums.ErrorCode;
import com.example.backend.enums.UserRoleType;
import com.example.backend.exception.AppException;
import com.example.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        UserEntity user = userRepository.findById(Integer.parseInt(userId)).orElse(null);
        if(user == null){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return UserPrincipal.of(user);
    }


    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    public static class UserPrincipal implements UserDetails {
        private int userId;
        private String username;
        private String password;
        private UserRoleType roleType;
        private final Collection<GrantedAuthority> authorities;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            ArrayList<GrantedAuthority> authList = new ArrayList<>(authorities);
            return authList;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        public static UserPrincipal of(UserEntity user) {
            return new UserPrincipal(user.getUserId(), user.getUserName(), user.getPassword(), user.getRoleType(), Collections.EMPTY_LIST);
        }
    }
}
