package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;

    /** 버킷 리스트 참조 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bucket_list_id", nullable = false)
    private BucketListEntity bucketList;

    @Column(name = "tag_name", nullable = false, length = 50)
    private String tagName;

    @Column(name = "insert_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime insertDate;

    @Column(name = "update_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDate;
}
