package com.example.backend.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 같이 아는 친구 응답 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonFriendsResponseDto {
    private Integer userId1;
    private Integer userId2;
    private List<UserDto> commonFriends;
}
