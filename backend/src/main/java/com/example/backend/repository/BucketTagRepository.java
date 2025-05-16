package com.example.backend.repository;

import com.example.backend.entity.BucketListEntity;
import com.example.backend.entity.BucketTagEntity;
import com.example.backend.entity.TagEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface BucketTagRepository extends JpaRepository<BucketTagEntity, Long> {

    // 지정된 버킷리스트에 연결된 태그 엔티티 조회
    List<BucketTagEntity> findByBucketList(BucketListEntity bucketList);

    // 지정된 태그에 연결된 버킷태그 엔티티 조회
    List<BucketTagEntity> findByTag(TagEntity tag);

    // 이미 연결된 태그인지 확인
    boolean existsByBucketListAndTag(BucketListEntity bucketList, TagEntity tag);

    /**
     * 인기 태그 조회 (since 이후 사용량 집계)
     * JPQL: 태그명, COUNT(bt) -> Object[]{ tagName:String, count:Long }
     */
    @Query("SELECT t.tagName, COUNT(bt) " +
            "FROM BucketTagEntity bt JOIN bt.tag t " +
            "WHERE bt.createdAt >= :since " +
            "GROUP BY t.tagName " +
            "ORDER BY COUNT(bt) DESC")
    List<Object[]> countByTagSince(@Param("since") LocalDateTime since);

    /**
     * 태그별 전체 사용 횟수 between 기간
     */
    Integer countByTag_TagNameAndCreatedAtBetween(String tagName,
                                                  LocalDateTime start,
                                                  LocalDateTime end);

    /**
     * 기간별 사용량 집계 (월별)
     * JPQL로 YEAR, MONTH 함수 사용 (H2 및 MySQL 호환)
     * Object[]{ year:Integer, month:Integer, count:Long }
     */
    @Query("SELECT FUNCTION('TO_CHAR', bt.createdAt, 'YYYY-MM'), COUNT(bt) " +
            "FROM BucketTagEntity bt JOIN bt.tag t " +
            "WHERE t.tagName = :tagName " +
            "AND bt.createdAt BETWEEN :start AND :end " +
            "GROUP BY FUNCTION('TO_CHAR', bt.createdAt, 'YYYY-MM') " +
            "ORDER BY FUNCTION('TO_CHAR', bt.createdAt, 'YYYY-MM')")
    List<Object[]> countByPeriod(
            @Param("tagName") String tagName,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    /**
     * 관련 태그 추천 (동일 버킷리스트 내 co-occurrence)
     */
    @Query("SELECT bt2.tag " +
            "FROM BucketTagEntity bt1, BucketTagEntity bt2 " +
            "WHERE bt1.tag.tagName = :tagName " +
            "  AND bt1.bucketList = bt2.bucketList " +
            "  AND bt2.tag.tagName <> :tagName " +
            "GROUP BY bt2.tag " +
            "ORDER BY COUNT(bt2) DESC")
    List<TagEntity> findRelatedTags(@Param("tagName") String tagName,
                                    Pageable pageable);
}
