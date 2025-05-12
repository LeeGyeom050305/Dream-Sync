package com.example.backend.dto.tag;

import lombok.*;

/**
 * 단일 태그 정보
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagDto {
    private Integer tagId;
    private String tagName;
}
