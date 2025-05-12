package com.example.backend.controller;

import com.example.backend.dto.BucketListDTO;
import com.example.backend.entity.BucketListEntity;
import com.example.backend.service.BucketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/buckets")
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

    @GetMapping("/sorted")
    public List<BucketListDTO> getAllSorted() {
        return bucketListService.getAllSortedByInsertDate().stream()
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

    @GetMapping("/done/y")
    public List<BucketListDTO> getBucketsDoneY() {
        return bucketListService.getBucketsByDoneStatusY().stream()
                .map(BucketListDTO::new)
                .collect(Collectors.toList());
    }

    // Read - 'N'인 버킷 리스트 조회
    @GetMapping("/done/n")
    public List<BucketListDTO> getBucketsDoneN() {
        return bucketListService.getBucketsByDoneStatusN().stream()
                .map(BucketListDTO::new)
                .collect(Collectors.toList());
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

    @PatchMapping("/{id}/done")
    public BucketListDTO markAsDone(@PathVariable Integer id) {
        BucketListEntity updated = bucketListService.markAsDone(id);
        return new BucketListDTO(updated);
    }

    @GetMapping("/search")
    public List<BucketListDTO> search(@RequestParam String keyword) {
        return bucketListService.searchByKeyword(keyword).stream()
                .map(BucketListDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/visible")
    public List<BucketListEntity> getBucketLists(@RequestParam Integer userId) {
        return bucketListService.getBucketListsByVisibility(userId);
    }

    // visible 상태에 맞춰 최신순으로 버킷리스트 조회
    @GetMapping("/visible/sorted")
    public List<BucketListEntity> getBucketListsSorted(@RequestParam Integer userId) {
        return bucketListService.getBucketListsByVisibilityAndSort(userId);
    }


}
