package com.example.backend.dto.tag;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 태그별 통계 요청
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagStatisticsRequest {
    private String tagName;
    // optional 기간: 시작일, 종료일
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}