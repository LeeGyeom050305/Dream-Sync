package com.example.backend.dto.tag;

import lombok.*;

/**
 * 해시태그 생성 응답
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CreateTagResponse {
    private TagDto tag;
}
