package com.example.backend.dto.tag;

import lombok.*;

/**
 * 관련 태그 추천 요청
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RelatedTagsRequest {
    private String tagName;
    private Integer limit;
}