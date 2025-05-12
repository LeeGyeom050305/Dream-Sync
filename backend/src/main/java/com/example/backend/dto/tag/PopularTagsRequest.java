package com.example.backend.dto.tag;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 인기/트렌딩 태그 요청
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PopularTagsRequest {
    // optional: 조회 시작일자 (ISO-8601)
    private LocalDateTime since;
    // optional: 결과 개수 제한
    private Integer limit;
}