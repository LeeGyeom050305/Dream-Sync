package com.example.backend.repository;

import com.example.backend.entity.BucketListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketListRepository extends JpaRepository<BucketListEntity, Integer> {
    // 기본 CRUD는 JpaRepository가 제공해줘요.
}