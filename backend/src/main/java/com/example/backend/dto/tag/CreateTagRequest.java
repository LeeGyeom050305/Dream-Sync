package com.example.backend.dto.tag;

import lombok.*;

/**
 * 해시태그 생성 요청 (없는 경우 새로 생성)
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateTagRequest {
    private Integer bucketListId;
    private String tagName;
}