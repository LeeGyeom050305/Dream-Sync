package com.example.backend.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 차단/차단 해제 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockUserDto {
    private Integer targetUserId;   // 차단하거나 해제할 대상 사용자 ID
}
