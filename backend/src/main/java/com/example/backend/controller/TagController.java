package com.example.backend.controller;

import com.example.backend.dto.tag.*;
import com.example.backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    /** 버킷리스트 ID로 태그들 호출 */
    @GetMapping("/bucket/{bucketListId}")
    public ResponseEntity<TagsByBucketResponse> getTagsByBucket(
            @PathVariable Integer bucketListId) {
        TagsByBucketResponse response = tagService.getTagsByBucket(bucketListId);
        return ResponseEntity.ok(response);
    }

    /** 태그로 버킷리스트 ID들 호출 */
    @GetMapping("/{tagName}/buckets")
    public ResponseEntity<BucketsByTagResponse> getBucketsByTag(
            @PathVariable String tagName) {
        BucketsByTagResponse response = tagService.getBucketsByTag(tagName);
        return ResponseEntity.ok(response);
    }

    /** 태그 생성 또는 기존 태그 버킷 연결 */
    @PostMapping
    public ResponseEntity<CreateTagResponse> addTag(
            @Valid @RequestBody CreateTagRequest request) {
        CreateTagResponse response = tagService.addTag(request);
        return ResponseEntity.ok(response);
    }

    /** 인기/트렌딩 태그 조회 */
    @GetMapping("/popular")
    public ResponseEntity<PopularTagsResponse> getPopularTags(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime since,
            @RequestParam(required = false) Integer limit) {
        PopularTagsRequest req = PopularTagsRequest.builder()
                .since(since)
                .limit(limit)
                .build();
        PopularTagsResponse response = tagService.getPopularTags(req);
        return ResponseEntity.ok(response);
    }

    /** 관련 태그 추천 */
    @GetMapping("/{tagName}/related")
    public ResponseEntity<RelatedTagsResponse> getRelatedTags(
            @PathVariable String tagName,
            @RequestParam(required = false) Integer limit) {
        RelatedTagsRequest req = RelatedTagsRequest.builder()
                .tagName(tagName)
                .limit(limit)
                .build();
        RelatedTagsResponse response = tagService.getRelatedTags(req);
        return ResponseEntity.ok(response);
    }

    /** 태그별 통계 */
    @GetMapping("/{tagName}/stats")
    public ResponseEntity<TagStatisticsResponse> getTagStatistics(
            @PathVariable String tagName,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate) {
        TagStatisticsRequest req = TagStatisticsRequest.builder()
                .tagName(tagName)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        TagStatisticsResponse response = tagService.getTagStatistics(req);
        return ResponseEntity.ok(response);
    }

    /** 자동완성·추천 */
    @GetMapping("/autocomplete")
    public ResponseEntity<AutocompleteResponse> autocomplete(
            @RequestParam String prefix,
            @RequestParam(required = false) Integer limit) {
        AutocompleteRequest req = AutocompleteRequest.builder()
                .prefix(prefix)
                .limit(limit)
                .build();
        AutocompleteResponse response = tagService.autocomplete(req);
        return ResponseEntity.ok(response);
    }
}
