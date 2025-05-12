package com.example.backend.repository;

import com.example.backend.entity.TagEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    Optional<TagEntity> findByTagName(String tagName);

    /** 자동완성: prefix로 시작하는 태그 이름 조회 */
    @Query("select t.tagName from TagEntity t " +
            "where t.tagName like concat(:prefix, '%') " +
            "order by t.createdAt desc")
    List<String> findTagNamesByPrefix(@Param("prefix") String prefix, Pageable pageable);
}