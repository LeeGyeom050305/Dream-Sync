package com.example.backend.repository;

import com.example.backend.entity.friend.FriendRequestEntity;
import com.example.backend.enums.FriendRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Integer> {

    // (보낸 OR 받은) AND 상태 = ACCEPTED
    List<FriendRequestEntity>
    findBySenderIdUserIdOrReceiverIdUserIdAndStatus(
            Integer senderUserId,
            Integer receiverUserId,
            FriendRequestStatus status
    );

    // 보낸 친구 요청 중 status가 ACCEPTED인 것
    List<FriendRequestEntity> findBySenderIdUserIdAndStatus(Integer senderUserId, FriendRequestStatus status);

    // 받은 친구 요청 중 status가 ACCEPTED인 것
    List<FriendRequestEntity> findByReceiverIdUserIdAndStatus(Integer receiverUserId, FriendRequestStatus status);


    // 공통 친구 ID 조회용
    @Query("""
      SELECT CASE 
               WHEN fr.senderId.userId = :userId THEN fr.receiverId.userId 
               ELSE fr.senderId.userId 
             END
        FROM FriendRequestEntity fr
       WHERE (fr.senderId.userId = :userId OR fr.receiverId.userId = :userId)
         AND fr.status = :status
    """)
    List<Integer> findFriendIdsByUserIdAndStatus(
            @Param("userId") Integer userId,
            @Param("status") FriendRequestStatus status
    );
}