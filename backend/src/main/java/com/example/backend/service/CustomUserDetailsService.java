package com.example.backend.service;

import com.example.backend.entity.UserEntity;
import com.example.backend.enums.ErrorCode;
import com.example.backend.exception.AppException;
import com.example.backend.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Spring Security 로그인 처리
     * @param userName the username identifying the user whose data is required.
     * @return authenticated UserDetails
     * @throws UsernameNotFoundException if the user could not be found
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return UserPrincipal.of(user);
    }

    /**
     * 사용자 정보를 Spring Security에 맞게 래핑한 UserPrincipal
     */
    @Getter
    @RequiredArgsConstructor
    public static class UserPrincipal implements UserDetails {
        private final Integer userId;
        private final String userName;
        private final String password;
        private final String roleType;
        private final String deleteYn;
        private final java.time.LocalDateTime insertDate;
        private final java.time.LocalDateTime updateDate;
        private final Collection<? extends GrantedAuthority> authorities;

        public static UserPrincipal of(UserEntity user) {
            List<GrantedAuthority> authList = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + user.getRoleType())
            );
            return new UserPrincipal(
                    user.getUserId(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getRoleType(),
                    user.getDeleteYn(),
                    user.getInsertDate(),
                    user.getUpdateDate(),
                    authList
            );
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getUsername() {
            return userName;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return !"Y".equals(deleteYn);
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return !"Y".equals(deleteYn);
        }
    }
}
