package com.example.backend.dto.tag;

import lombok.*;

import java.util.List;

/**
 * 관련 태그 추천 응답
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RelatedTagsResponse {
    private String tagName;
    private List<TagDto> relatedTags;
}