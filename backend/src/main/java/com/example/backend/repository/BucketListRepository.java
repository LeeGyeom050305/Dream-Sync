package com.example.backend.repository;

import com.example.backend.entity.BucketListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BucketListRepository extends JpaRepository<BucketListEntity, Integer> {

    List<BucketListEntity> findByBucketDone(Boolean  bucketDone);
    List<BucketListEntity> findByContentsContainingIgnoreCase(String keyword);
    List<BucketListEntity> findByContentsContainingIgnoreCaseOrderByInsertDateDesc(String keyword);
    List<BucketListEntity> findAllByOrderByInsertDateDesc();
    List<BucketListEntity> findByBucketDoneOrderByInsertDateDesc(Boolean bucketDone);
    List<BucketListEntity> findByVisibleTrueOrUserIdUserId(Integer userId);
    List<BucketListEntity> findByVisibleTrueOrUserIdUserIdOrderByInsertDateDesc(Integer userId);

}
