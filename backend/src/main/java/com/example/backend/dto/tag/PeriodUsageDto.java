package com.example.backend.dto.tag;

import lombok.*;

/**
 * 기간별 사용량 DTO
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PeriodUsageDto {
    private String period;        // ex: "2025-05", "2025-05-08"
    private Integer usageCount;
}
