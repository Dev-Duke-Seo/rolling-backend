package com.blob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.blob.dto.request.PostCreateRequest;
import com.blob.dto.request.ReportRequest;
import com.blob.dto.response.*;
import com.blob.entity.*;
import com.blob.repository.*;
import com.blob.service.PostService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final BlobUserRepository blobUserRepository;
    private final LikeRepository likeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ReportRepository reportRepository;

    @Override
    public BlobApiResponse<PostResponse> createPost(Long userId, List<MultipartFile> images,
            PostCreateRequest request) {
        BlobUser user = blobUserRepository.findActiveUserById(userId).orElse(null);

        if (user == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        Post post = Post.builder().user(user).title(request.getTitle())
                .content(request.getContent()).category(request.getCategory())
                .subcategory(request.getSubcategory()).latitude(request.getLatitude())
                .longitude(request.getLongitude()).address(request.getAddress()).build();

        Post savedPost = postRepository.save(post);

        // TODO: 이미지 업로드 처리
        if (images != null && !images.isEmpty()) {
            // 이미지 업로드 로직 구현 필요
        }

        return BlobApiResponse.success(PostResponse.from(savedPost));
    }

    @Override
    @Transactional(readOnly = true)
    public BlobApiResponse<PostDetailResponse> getPost(Long postId) {
        Post post = postRepository.findActivePostById(postId).orElse(null);

        if (post == null) {
            return BlobApiResponse.error(404, "게시물을 찾을 수 없습니다", "POST_001");
        }

        // 조회수 증가
        post.incrementViewCount();
        postRepository.save(post);

        return BlobApiResponse.success(PostDetailResponse.from(post));
    }

    @Override
    public BlobApiResponse<Void> deletePost(Long userId, Long postId) {
        Post post = postRepository.findActivePostById(postId).orElse(null);

        if (post == null) {
            return BlobApiResponse.error(404, "게시물을 찾을 수 없습니다", "POST_001");
        }

        if (!post.getUser().getUserId().equals(userId)) {
            return BlobApiResponse.error(403, "게시물 삭제 권한이 없습니다", "POST_002");
        }

        post.setIsDeleted(true);
        postRepository.save(post);

        return BlobApiResponse.success(null);
    }

    @Override
    @Transactional(readOnly = true)
    public BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> getFeed(int page, int size,
            Double cityLat, Double cityLng, String sortBy, String categories, String startDate,
            String endDate, boolean hasImage, boolean hasLocation, int minLikes, String keyword) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage;

        // TODO: 복잡한 필터링 로직 구현
        // 현재는 기본 조회만 구현
        if (keyword != null && !keyword.trim().isEmpty()) {
            postPage = postRepository.findActivePostsByKeyword(keyword, pageable);
        } else if (categories != null && !categories.trim().isEmpty()) {
            postPage = postRepository.findActivePostsByCategory(categories, pageable);
        } else {
            postPage = postRepository.findAllActivePosts(pageable);
        }

        Page<PostSummaryResponse> responsePage = postPage.map(PostSummaryResponse::from);
        BlobPagedResponse<PostSummaryResponse> pagedResponse = BlobPagedResponse.from(responsePage);

        return BlobApiResponse.success(pagedResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public BlobApiResponse<List<MarkerDataResponse>> getMarkers(String categories, double minLat,
            double maxLat, double minLng, double maxLng) {

        List<Post> posts = postRepository.findActivePostsInArea(minLat, maxLat, minLng, maxLng);
        List<MarkerDataResponse> markers =
                posts.stream().map(MarkerDataResponse::from).collect(Collectors.toList());

        return BlobApiResponse.success(markers);
    }

    @Override
    public BlobApiResponse<LikeResponse> togglePostLike(Long userId, Long postId) {
        Post post = postRepository.findActivePostById(postId).orElse(null);

        if (post == null) {
            return BlobApiResponse.error(404, "게시물을 찾을 수 없습니다", "POST_001");
        }

        boolean isLiked = likeRepository.existsByUserUserIdAndTargetTypeAndTargetId(userId,
                Like.LikeTargetType.POST, postId);

        if (isLiked) {
            likeRepository.deleteByUserUserIdAndTargetTypeAndTargetId(userId,
                    Like.LikeTargetType.POST, postId);
            post.decrementLikeCount();
        } else {
            BlobUser user = blobUserRepository.findActiveUserById(userId).orElse(null);
            if (user != null) {
                Like like = Like.builder().user(user).targetType(Like.LikeTargetType.POST)
                        .targetId(postId).build();
                likeRepository.save(like);
                post.incrementLikeCount();
            }
        }

        postRepository.save(post);

        return BlobApiResponse.success(LikeResponse.of(!isLiked, post.getLikeCount()));
    }

    @Override
    public BlobApiResponse<BookmarkResponse> toggleBookmark(Long userId, Long postId) {
        Post post = postRepository.findActivePostById(postId).orElse(null);

        if (post == null) {
            return BlobApiResponse.error(404, "게시물을 찾을 수 없습니다", "POST_001");
        }

        boolean isBookmarked = bookmarkRepository.existsByUserUserIdAndPostPostId(userId, postId);

        if (isBookmarked) {
            bookmarkRepository.deleteByUserUserIdAndPostPostId(userId, postId);
        } else {
            BlobUser user = blobUserRepository.findActiveUserById(userId).orElse(null);
            if (user != null) {
                Bookmark bookmark = Bookmark.builder().user(user).post(post).build();
                bookmarkRepository.save(bookmark);
            }
        }

        return BlobApiResponse.success(BookmarkResponse.of(!isBookmarked));
    }

    @Override
    public BlobApiResponse<Void> reportPost(Long userId, Long postId, ReportRequest request) {
        Post post = postRepository.findActivePostById(postId).orElse(null);

        if (post == null) {
            return BlobApiResponse.error(404, "게시물을 찾을 수 없습니다", "POST_001");
        }

        BlobUser reporter = blobUserRepository.findActiveUserById(userId).orElse(null);

        if (reporter == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        // 중복 신고 체크
        boolean alreadyReported = reportRepository.existsByReporterUserIdAndTargetTypeAndTargetId(
                userId, Report.ReportTargetType.POST, postId);

        if (alreadyReported) {
            return BlobApiResponse.error(400, "이미 신고한 게시물입니다", "REPORT_001");
        }

        Report report = Report.builder().reporter(reporter).targetType(Report.ReportTargetType.POST)
                .targetId(postId).reason(request.getReason()).description(request.getDescription())
                .build();

        reportRepository.save(report);

        return BlobApiResponse.success(null);
    }
}
