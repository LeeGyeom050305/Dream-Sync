package com.example.backend.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 친구 요청 수락/거절 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendRequestActionDto {
    private Integer requestId;      // 처리할 친구 요청 ID
}