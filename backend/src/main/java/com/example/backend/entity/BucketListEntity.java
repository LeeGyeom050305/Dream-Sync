package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * 버킷 리스트 엔티티
 */
@Entity
@Table(name = "bucket_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_list_id")
    private Integer bucketListId;

    /** 사용자 참조 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @Column(name = "contents", length = 200)
    private String contents;

    @Column(name = "default_seq")
    private Integer defaultSeq;

    @Column(name = "bucket_done", length = 1)
    private String bucketDone;

    @Column(name = "insert_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime insertDate;

    @Column(name = "update_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDate;
}
