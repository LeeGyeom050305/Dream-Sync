package com.example.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FriendRequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED;
}