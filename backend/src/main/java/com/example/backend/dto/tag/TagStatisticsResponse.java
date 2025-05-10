package com.example.backend.dto.tag;

import lombok.*;

import java.util.List;

/**
 * 태그별 통계 응답
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagStatisticsResponse {
    private String tagName;
    private Integer totalUsage;
    private List<PeriodUsageDto> usageByPeriod;
}