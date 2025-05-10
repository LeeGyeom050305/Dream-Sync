package com.example.backend.service;

import com.example.backend.dto.tag.*;
import com.example.backend.entity.BucketListEntity;
import com.example.backend.entity.BucketTagEntity;
import com.example.backend.entity.TagEntity;
import com.example.backend.enums.ErrorCode;
import com.example.backend.exception.AppException;
import com.example.backend.repository.BucketListRepository;
import com.example.backend.repository.BucketTagRepository;
import com.example.backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final BucketTagRepository bucketTagRepository;
    private final BucketListRepository bucketListRepository;

    /** 버킷리스트 ID로 태그들 호출 */
    @Transactional(readOnly = true)
    public TagsByBucketResponse getTagsByBucket(Integer bucketListId) {
        BucketListEntity bucket = bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND, "BucketList not found"));
        List<TagDto> tags = bucketTagRepository.findByBucketList(bucket)
                .stream()
                .map(bt -> TagDto.builder()
                        .tagId(bt.getTag().getTagId())
                        .tagName(bt.getTag().getTagName())
                        .build())
                .collect(Collectors.toList());
        return TagsByBucketResponse.builder()
                .bucketListId(bucketListId)
                .tags(tags)
                .build();
    }

    /** 태그로 버킷리스트 ID들 호출 */
    @Transactional(readOnly = true)
    public BucketsByTagResponse getBucketsByTag(String tagName) {
        TagEntity tag = tagRepository.findByTagName(tagName)
                .orElseThrow(() -> new AppException(ErrorCode.CODE_NOT_FOUND, "Tag not found"));
        List<Integer> bucketIds = bucketTagRepository.findByTag(tag)
                .stream()
                .map(bt -> bt.getBucketList().getBucketListId())
                .collect(Collectors.toList());
        return BucketsByTagResponse.builder()
                .tagName(tagName)
                .bucketListIds(bucketIds)
                .build();
    }

    /** 태그 생성 또는 기존 태그 버킷 연결 */
    @Transactional
    public CreateTagResponse addTag(CreateTagRequest req) {
        BucketListEntity bucket = bucketListRepository.findById(req.getBucketListId())
                .orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND, "BucketList not found"));
        TagEntity tag = tagRepository.findByTagName(req.getTagName())
                .orElseGet(() -> tagRepository.save(
                        TagEntity.builder().tagName(req.getTagName()).createdAt(LocalDateTime.now()).build()
                ));
        if (bucketTagRepository.existsByBucketListAndTag(bucket, tag)) {
            return CreateTagResponse.builder()
                    .tag(TagDto.builder().tagId(tag.getTagId()).tagName(tag.getTagName()).build())
                    .build();
        }
        bucketTagRepository.save(
                BucketTagEntity.builder().bucketList(bucket).tag(tag).createdAt(LocalDateTime.now()).build()
        );
        return CreateTagResponse.builder()
                .tag(TagDto.builder().tagId(tag.getTagId()).tagName(tag.getTagName()).build())
                .build();
    }

    /** 인기/트렌딩 태그 조회 */
    @Transactional(readOnly = true)
    public PopularTagsResponse getPopularTags(PopularTagsRequest req) {
        LocalDateTime since = Optional.ofNullable(req.getSince()).orElse(LocalDateTime.now().minusDays(7));
        int limit = Optional.ofNullable(req.getLimit()).orElse(10);
        List<Pair<String, Integer>> pairs = bucketTagRepository.countByTagSince(since);
        List<PopularTagDto> list = pairs.stream()
                .limit(limit)
                .map(p -> PopularTagDto.builder()
                        .tagName(p.getFirst())
                        .usageCount(p.getSecond())
                        .build())
                .collect(Collectors.toList());
        return PopularTagsResponse.builder().tags(list).build();
    }

    /** 관련 태그 추천 */
    @Transactional(readOnly = true)
    public RelatedTagsResponse getRelatedTags(RelatedTagsRequest req) {
        String tagName = req.getTagName();
        int limit = Optional.ofNullable(req.getLimit()).orElse(5);
        List<TagEntity> related = bucketTagRepository.findRelatedTags(tagName, PageRequest.of(0, limit));
        List<TagDto> relatedDtos = related.stream()
                .map(tag -> TagDto.builder()
                        .tagId(tag.getTagId())
                        .tagName(tag.getTagName())
                        .build())
                .collect(Collectors.toList());
        return RelatedTagsResponse.builder()
                .tagName(tagName)
                .relatedTags(relatedDtos)
                .build();
    }

    /** 태그별 통계 */
    @Transactional(readOnly = true)
    public TagStatisticsResponse getTagStatistics(TagStatisticsRequest req) {
        String tagName = req.getTagName();
        LocalDateTime start = Optional.ofNullable(req.getStartDate()).orElse(LocalDateTime.MIN);
        LocalDateTime end = Optional.ofNullable(req.getEndDate()).orElse(LocalDateTime.now());
        Integer total = bucketTagRepository.countByTag_TagNameAndCreatedAtBetween(tagName, start, end);
        String pattern = "%Y-%m";
        List<Pair<String, Integer>> stats = bucketTagRepository.countByPeriod(tagName, start, end, pattern);
        List<PeriodUsageDto> periods = stats.stream()
                .map(p -> PeriodUsageDto.builder()
                        .period(p.getFirst())
                        .usageCount(p.getSecond())
                        .build())
                .collect(Collectors.toList());
        return TagStatisticsResponse.builder()
                .tagName(tagName)
                .totalUsage(total)
                .usageByPeriod(periods)
                .build();
    }

    /** 자동완성·추천 */
    @Transactional(readOnly = true)
    public AutocompleteResponse autocomplete(AutocompleteRequest req) {
        int limit = Optional.ofNullable(req.getLimit()).orElse(10);
        List<String> suggestions = tagRepository.findTagNamesByPrefix(req.getPrefix(), PageRequest.of(0, limit));
        return AutocompleteResponse.builder()
                .suggestions(suggestions)
                .build();
    }
}
