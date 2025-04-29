package com.example.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserRoleType {
    SADMIN("SADMIN", "슈퍼 관리자"),
    ADMIN("ADMIN", "관리자"),
    USER("USER", "사용자"),
    PENDING("PENDING", "승인 대기"),
    REJECTED("REJECTED", "승인 거절");

    private final String code;
    private final String displayName;

    public static UserRoleType of(String code) {
        return Arrays.stream(UserRoleType.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(USER);
    }

    public boolean isApproved() {
        return this == ADMIN || this == USER;
    }

    public boolean isPending() {
        return this == PENDING;
    }
}
