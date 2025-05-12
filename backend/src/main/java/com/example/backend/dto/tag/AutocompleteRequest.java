package com.example.backend.dto.tag;

import lombok.*;

/**
 * 자동완성·추천 요청
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AutocompleteRequest {
    private String prefix;
    private Integer limit;
}