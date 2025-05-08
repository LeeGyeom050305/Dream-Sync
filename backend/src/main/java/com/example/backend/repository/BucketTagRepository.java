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
import org.springframework.data.util.Pair;

public interface BucketTagRepository extends JpaRepository<BucketTagEntity, Long> {

    List<BucketTagEntity> findByBucketList(BucketListEntity bucketList);

    List<BucketTagEntity> findByTag(TagEntity tag);

    boolean existsByBucketListAndTag(BucketListEntity bucketList, TagEntity tag);

    /** 인기 태그 조회 (since 이후 사용량 집계) */
    @Query("select new org.springframework.data.util.Pair(t.tagName, count(bt)) " +
            "from BucketTagEntity bt join bt.tag t " +
            "where bt.createdAt >= :since " +
            "group by t.tagName " +
            "order by count(bt) desc")
    List<Pair<String, Integer>> countByTagSince(@Param("since") LocalDateTime since);

    /** 태그별 전체 사용 횟수 between 기간 */
    Integer countByTag_TagNameAndCreatedAtBetween(String tagName, LocalDateTime start, LocalDateTime end);

    /** 기간별 사용량 집계 (월별) */
    @Query(value = "select function('date_format', bt.createdAt, :pattern) as period, count(bt) as count " +
            "from BucketTagEntity bt join bt.tag t " +
            "where t.tagName = :tagName and bt.createdAt between :start and :end " +
            "group by function('date_format', bt.createdAt, :pattern)", nativeQuery = true)
    List<Pair<String, Integer>> countByPeriod(
            @Param("tagName") String tagName,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("pattern") String pattern);

    /** 관련 태그 추천 (동일 버킷리스트 내 co-occurrence) */
    @Query("select bt2.tag from BucketTagEntity bt1, BucketTagEntity bt2 " +
            "where bt1.tag.tagName = :tagName and bt1.bucketList = bt2.bucketList " +
            "and bt2.tag.tagName <> :tagName " +
            "group by bt2.tag " +
            "order by count(bt2) desc")
    List<TagEntity> findRelatedTags(@Param("tagName") String tagName, Pageable pageable);
}
