package com.example.backend.repository;

import com.example.backend.entity.friend.UserBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBlockRepository extends JpaRepository<UserBlockEntity, Integer> {
    Optional<UserBlockEntity> findByBlockerUserIdAndBlockedUserId(Integer blockerId, Integer blockedId);
}
