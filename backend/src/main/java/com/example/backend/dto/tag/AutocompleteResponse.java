package com.example.backend.dto.tag;

import lombok.*;

import java.util.List;

/**
 * 자동완성·추천 응답
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AutocompleteResponse {
    private List<String> suggestions;
}
