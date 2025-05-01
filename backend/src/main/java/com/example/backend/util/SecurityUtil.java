package com.example.backend.util;

import com.example.backend.enums.ErrorCode;
import com.example.backend.enums.UserRoleType;
import com.example.backend.exception.AppException;
import com.example.backend.service.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private SecurityUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static int currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AppException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        CustomUserDetailsService.UserPrincipal principal = (CustomUserDetailsService.UserPrincipal) authentication.getPrincipal();
        return principal.getUserId();
    }

    public static UserRoleType currentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AppException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        CustomUserDetailsService.UserPrincipal principal = (CustomUserDetailsService.UserPrincipal) authentication.getPrincipal();
        return principal.getRoleType();
    }

    public static String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AppException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        CustomUserDetailsService.UserPrincipal principal = (CustomUserDetailsService.UserPrincipal) authentication.getPrincipal();
        return principal.getUsername();
    }
}
