package com.example.backend.service;

import com.example.backend.entity.BucketListEntity;
import com.example.backend.repository.BucketListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BucketListService {
    private final BucketListRepository bucketListRepository;

    // Create
    public BucketListEntity createBucket(BucketListEntity bucket) {
        return bucketListRepository.save(bucket);
    }

    // Read - 전체 조회
    public List<BucketListEntity> getAllBuckets() {
        return bucketListRepository.findAll();
    }

    // Read - ID로 조회
    public Optional<BucketListEntity> getBucketById(Integer id) {
        return bucketListRepository.findById(id);
    }

    // Update
    public BucketListEntity updateBucket(BucketListEntity bucket) {
        return bucketListRepository.save(bucket); // ID 있으면 수정, 없으면 생성
    }

    // Delete
    public void deleteBucket(Integer id) {
        bucketListRepository.deleteById(id);
    }
}