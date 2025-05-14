package com.example.backend.controller;

import com.example.backend.dto.friend.*;
import com.example.backend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 요청 보내기
    @PostMapping("/request")
    public ResponseEntity<Void> sendFriendRequest(@RequestBody FriendRequestDto dto,
                                                  @RequestParam Integer currentUserId) {
        friendService.sendRequest(dto, currentUserId);
        return ResponseEntity.ok().build();
    }

    // 친구 요청 수락
    @PostMapping("/accept")
    public ResponseEntity<Void> acceptFriendRequest(@RequestBody FriendRequestActionDto dto,
                                                    @RequestParam Integer currentUserId) {
        friendService.acceptRequest(dto, currentUserId);
        return ResponseEntity.ok().build();
    }

    // 친구 요청 거절
    @PostMapping("/reject")
    public ResponseEntity<Void> rejectFriendRequest(@RequestBody FriendRequestActionDto dto,
                                                    @RequestParam Integer currentUserId) {
        friendService.rejectRequest(dto, currentUserId);
        return ResponseEntity.ok().build();
    }

    // 사용자 차단
    @PostMapping("/block")
    public ResponseEntity<Void> blockUser(@RequestBody BlockUserDto dto,
                                          @RequestParam Integer currentUserId) {
        friendService.blockUser(dto, currentUserId);
        return ResponseEntity.ok().build();
    }

    // 사용자 차단 해제
    @PostMapping("/unblock")
    public ResponseEntity<Void> unblockUser(@RequestBody BlockUserDto dto,
                                            @RequestParam Integer currentUserId) {
        friendService.unblockUser(dto, currentUserId);
        return ResponseEntity.ok().build();
    }

    // 친구 목록 조회
    @GetMapping("/list")
    public ResponseEntity<FriendListResponseDto> getFriendList(@RequestParam Integer currentUserId) {
        return ResponseEntity.ok(friendService.getFriendList(currentUserId));
    }

    // 공통 친구 조회
    @GetMapping("/common")
    public ResponseEntity<CommonFriendsResponseDto> getCommonFriends(@RequestParam Integer currentUserId,
                                                                     @RequestParam Integer otherUserId) {
        return ResponseEntity.ok(friendService.getCommonFriends(currentUserId, otherUserId));
    }
}
