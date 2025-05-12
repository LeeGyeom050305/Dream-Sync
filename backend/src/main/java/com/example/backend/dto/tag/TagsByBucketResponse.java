package com.example.backend.dto.tag;

import lombok.*;

import java.util.List;

/**
 * 버킷리스트 ID로 태그 조회 응답
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagsByBucketResponse {
    private Integer bucketListId;
    private List<TagDto> tags;
}