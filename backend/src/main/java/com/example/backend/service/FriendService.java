package com.example.backend.service;

import com.example.backend.dto.friend.*;
import com.example.backend.entity.UserEntity;
import com.example.backend.entity.friend.*;
import com.example.backend.entity.friend.UserBlockEntity;
import com.example.backend.enums.FriendRequestStatus;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRequestRepository friendRequestRepo;
    private final UserBlockRepository userBlockRepo;
    private final UserRepository userRepo;

    @Transactional
    public void sendRequest(FriendRequestDto dto, Integer currentUserId) {
        UserEntity sender = userRepo.findById(currentUserId).orElseThrow();
        UserEntity receiver = userRepo.findById(dto.getTargetUserId()).orElseThrow();
        FriendRequestEntity req = FriendRequestEntity.builder()
                .senderId(sender)
                .receiverId(receiver)
                .status(FriendRequestStatus.PENDING)
                .build();
        friendRequestRepo.save(req);
    }

    @Transactional
    public void acceptRequest(FriendRequestActionDto dto, Integer currentUserId) {
        FriendRequestEntity req = friendRequestRepo.findById(dto.getRequestId()).orElseThrow();
        if (!req.getReceiverId().getUserId().equals(currentUserId)) {
            throw new RuntimeException("Unauthorized");
        }
        req.setStatus(FriendRequestStatus.ACCEPTED);
        friendRequestRepo.save(req);
    }

    @Transactional
    public void rejectRequest(FriendRequestActionDto dto, Integer currentUserId) {
        FriendRequestEntity req = friendRequestRepo.findById(dto.getRequestId()).orElseThrow();
        if (!req.getReceiverId().getUserId().equals(currentUserId)) {
            throw new RuntimeException("Unauthorized");
        }
        req.setStatus(FriendRequestStatus.REJECTED);
        friendRequestRepo.save(req);
    }

    @Transactional
    public void blockUser(BlockUserDto dto, Integer currentUserId) {
        UserEntity blocker = userRepo.findById(currentUserId).orElseThrow();
        UserEntity blocked = userRepo.findById(dto.getTargetUserId()).orElseThrow();
        UserBlockEntity ub = UserBlockEntity.builder()
                .blocker(blocker)
                .blocked(blocked)
                .build();
        userBlockRepo.save(ub);
    }

    @Transactional
    public void unblockUser(BlockUserDto dto, Integer currentUserId) {
        UserBlockEntity ub = userBlockRepo.findByBlockerUserIdAndBlockedUserId(
                        currentUserId, dto.getTargetUserId())
                .orElseThrow();
        userBlockRepo.delete(ub);
    }

    @Transactional(readOnly = true)
    public FriendListResponseDto getFriendListById(Integer userId) {
        // 보낸 요청 중 ACCEPTED
        List<FriendRequestEntity> sentAccepted = friendRequestRepo
                .findBySenderIdUserIdAndStatus(userId, FriendRequestStatus.ACCEPTED);
        // 받은 요청 중 ACCEPTED
        List<FriendRequestEntity> receivedAccepted = friendRequestRepo
                .findByReceiverIdUserIdAndStatus(userId, FriendRequestStatus.ACCEPTED);

        // 두 리스트 합치기
        List<FriendRequestEntity> accepted = new ArrayList<>();
        accepted.addAll(sentAccepted);
        accepted.addAll(receivedAccepted);

        List<UserDto> friends = accepted.stream()
                .map(req -> req.getSenderId().getUserId().equals(userId)
                        ? req.getReceiverId()
                        : req.getSenderId())
                .map(user -> UserDto.builder()
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());

        return FriendListResponseDto.builder()
                .friends(friends)
                .build();
    }

    @Transactional(readOnly = true)
    public CommonFriendsResponseDto getCommonFriends(Integer userId1, Integer userId2) {
        // 수정된 메서드명 사용
        List<Integer> list1 = friendRequestRepo.findFriendIdsByUserIdAndStatus(
                userId1, FriendRequestStatus.ACCEPTED);
        List<Integer> list2 = friendRequestRepo.findFriendIdsByUserIdAndStatus(
                userId2, FriendRequestStatus.ACCEPTED);

        List<UserDto> common = list1.stream()
                .filter(list2::contains)
                .map(id -> userRepo.findById(id).orElseThrow())
                .map(user -> UserDto.builder()
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());

        return CommonFriendsResponseDto.builder()
                .userId1(userId1)
                .userId2(userId2)
                .commonFriends(common)
                .build();
    }
}