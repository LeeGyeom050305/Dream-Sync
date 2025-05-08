package com.example.backend.dto.tag;

import lombok.*;

/**
 * 인기 태그 정보
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PopularTagDto {
    private String tagName;
    private Integer usageCount;
}