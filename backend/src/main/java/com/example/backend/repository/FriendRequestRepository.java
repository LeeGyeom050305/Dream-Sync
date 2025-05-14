package com.example.backend.repository;

import com.example.backend.entity.friend.FriendRequestEntity;
import com.example.backend.enums.FriendRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Integer> {

    // 현재 사용자가 보낸 요청과 받은 요청 중에서 ACCEPTED 상태인 모든 요청 가져오기
//    @Query("SELECT fr FROM FriendRequestEntity fr " +
//            "WHERE (fr.senderId.userId = :userId OR fr.receiverId.userId = :userId) " +
//            "AND fr.status = :status")
//    List<FriendRequestEntity> findByUserIdAndStatus(@Param("userId") Integer userId,
//                                                    @Param("status") FriendRequestStatus status);
    @Query("SELECT fr FROM FriendRequestEntity fr " +
            "WHERE (fr.senderId.userId = :userId OR fr.receiverId.userId = :userId) " +
            "AND fr.status = :status")
    List<FriendRequestEntity> findByUserIdAndStatus(@Param("userId") Integer userId,
                                                    @Param("status") FriendRequestStatus status);


    // 친구 ID 목록 반환 (공통 친구용)
    @Query("SELECT CASE " +
            "WHEN fr.senderId.userId = :userId THEN fr.receiverId.userId " +
            "ELSE fr.senderId.userId " +
            "END " +
            "FROM FriendRequestEntity fr " +
            "WHERE (fr.senderId.userId = :userId OR fr.receiverId.userId = :userId) " +
            "AND fr.status = :status")
    List<Integer> findFriendIdsByUserIdAndStatus(@Param("userId") Integer userId,
                                                 @Param("status") FriendRequestStatus status);
}