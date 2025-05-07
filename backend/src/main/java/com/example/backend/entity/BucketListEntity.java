package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 버킷 리스트 엔티티
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "bucket_done", nullable = false)
    private Boolean bucketDone;

    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @Column(name = "insert_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime insertDate;

    @Column(name = "update_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDate;

    @ElementCollection
    @CollectionTable(name = "bucket_list_numbers", joinColumns = @JoinColumn(name = "bucket_list_id"))
    @Column(name = "number_value")
    private List<Integer> numberArray;

}
