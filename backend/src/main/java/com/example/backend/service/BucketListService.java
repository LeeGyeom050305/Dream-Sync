package com.example.backend.service;

import com.example.backend.entity.BucketListEntity;
import com.example.backend.repository.BucketListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public List<BucketListEntity> getBucketsByDoneStatusY() {
        return bucketListRepository.findByBucketDone("Y");
    }

    public List<BucketListEntity> getBucketsByDoneStatusN() {
        return bucketListRepository.findByBucketDone("N");
    }

    // Update
    public BucketListEntity updateBucket(BucketListEntity bucket) {
        return bucketListRepository.save(bucket); // ID 있으면 수정, 없으면 생성
    }

    // Delete
    public void deleteBucket(Integer id) {
        bucketListRepository.deleteById(id);
    }

    @Transactional
    public BucketListEntity markAsDone(Integer id) {
        BucketListEntity bucket = bucketListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("버킷을 찾을 수 없습니다."));

        if ("Y".equals(bucket.getBucketDone())) {
            throw new IllegalStateException("이미 완료된 버킷입니다.");
        }

        bucket.setBucketDone("Y");
        bucket.setUpdateDate(LocalDateTime.now());

        return bucketListRepository.save(bucket);
    }

    public List<BucketListEntity> searchByKeyword(String keyword) {
        return bucketListRepository.findByContentsContainingIgnoreCase(keyword);
    }





}