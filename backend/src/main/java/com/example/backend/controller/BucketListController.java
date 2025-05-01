package com.example.backend.controller;

import com.example.backend.entity.BucketListEntity;
import com.example.backend.service.BucketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@RequiredArgsConstructor
public class BucketListController {
    private final BucketListService bucketListService;

    // Create
    @PostMapping
    public BucketListEntity create(@RequestBody BucketListEntity bucket) {
        return bucketListService.createBucket(bucket);
    }

    // Read all
    @GetMapping
    public List<BucketListEntity> getAll() {
        return bucketListService.getAllBuckets();
    }

    // Read by ID
    @GetMapping("/{id}")
    public BucketListEntity getById(@PathVariable Integer id) {
        return bucketListService.getBucketById(id)
                .orElseThrow(() -> new RuntimeException("찾을 수 없습니다."));
    }

    // Update
    @PutMapping("/{id}")
    public BucketListEntity update(@PathVariable Integer id, @RequestBody BucketListEntity updated) {
        updated.setBucketListId(id);
        return bucketListService.updateBucket(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bucketListService.deleteBucket(id);
    }
}