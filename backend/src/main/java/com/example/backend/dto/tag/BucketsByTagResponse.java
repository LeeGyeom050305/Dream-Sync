package com.example.backend.dto.tag;

import lombok.*;

import java.util.List;

/**
 * 태그로 버킷리스트 ID들 조회 응답
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BucketsByTagResponse {
    private String tagName;
    private List<Integer> bucketListIds;
}