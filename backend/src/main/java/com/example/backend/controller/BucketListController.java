package com.example.backend.controller;

import com.example.backend.dto.BucketListDTO;
import com.example.backend.entity.BucketListEntity;
import com.example.backend.service.BucketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buckets")
@RequiredArgsConstructor
public class BucketListController {

    private final BucketListService bucketListService;

    // Create
    @PostMapping
    public BucketListDTO create(@RequestBody BucketListEntity bucket) {
        BucketListEntity created = bucketListService.createBucket(bucket);
        return new BucketListDTO(created);
    }

    // Read all
    @GetMapping
    public List<BucketListDTO> getAll() {
        return bucketListService.getAllBuckets().stream()
                .map(BucketListDTO::new)
                .collect(Collectors.toList());
    }

    // Read by ID
    @GetMapping("/{id}")
    public BucketListDTO getById(@PathVariable Integer id) {
        BucketListEntity bucket = bucketListService.getBucketById(id)
                .orElseThrow(() -> new RuntimeException("찾을 수 없습니다."));
        return new BucketListDTO(bucket);
    }

    // Update
    @PutMapping("/{id}")
    public BucketListDTO update(@PathVariable Integer id, @RequestBody BucketListEntity updated) {
        updated.setBucketListId(id);
        BucketListEntity result = bucketListService.updateBucket(updated);
        return new BucketListDTO(result);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bucketListService.deleteBucket(id);
    }
}
