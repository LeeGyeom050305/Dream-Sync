package com.example.backend.dto.tag;

import lombok.*;

import java.util.List;

/**
 * 인기/트렌딩 태그 응답
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PopularTagsResponse {
    private List<PopularTagDto> tags;
}