package com.example.backend.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 친구 요청 전송 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendRequestDto {
    private Integer targetUserId; // 친구 요청을 보낼 대상 사용자 ID
}
