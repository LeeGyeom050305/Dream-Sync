package com.example.backend.dto;

import com.example.backend.entity.BucketListEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "버킷 리스트 응답 DTO")
public class BucketListDTO {

    @Schema(description = "버킷 리스트 ID", example = "1")
    private Integer bucketListId;

    @Schema(description = "사용자 ID", example = "3")
    private Integer userId;

    @Schema(description = "버킷 리스트 내용", example = "스카이다이빙 도전!")
    private String contents;

    @Schema(description = "기본 순서", example = "1")
    private Integer defaultSeq;

    @Schema(description = "완료 여부", example = "true")
    private Boolean bucketDone;

    @Schema(description = "다른 사용자에게 표시 여부", example = "true")
    private Boolean visible;

    @Schema(description = "등록일", example = "2025-05-01T12:00:00")
    private LocalDateTime insertDate;

    @Schema(description = "수정일", example = "2025-05-02T09:00:00")
    private LocalDateTime updateDate;

    @Schema(description = "숫자 배열", example = "[1, 2, 3]")
    private List<Integer> numberArray;

    public BucketListDTO(BucketListEntity entity) {
        this.bucketListId = entity.getBucketListId();
        this.userId = entity.getUserId().getUserId(); // Lazy 로딩 문제 발생 가능
        this.contents = entity.getContents();
        this.defaultSeq = entity.getDefaultSeq();
        this.bucketDone = entity.getBucketDone();
        this.visible = entity.getVisible();
        this.insertDate = entity.getInsertDate();
        this.updateDate = entity.getUpdateDate();
        this.numberArray = entity.getNumberArray();
    }
}
