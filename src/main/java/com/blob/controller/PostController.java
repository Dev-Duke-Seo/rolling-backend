package com.blob.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.blob.dto.request.PostCreateRequest;
import com.blob.dto.request.ReportRequest;
import com.blob.dto.response.*;
import com.blob.service.PostService;
import java.util.List;

@RestController
@RequestMapping("/blob/posts")
@RequiredArgsConstructor
@Tag(name = "BLOB Post", description = "BLOB 게시물 관련 API")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시물 생성", description = "새로운 게시물을 생성합니다")
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<PostResponse>> createPost(
            @RequestPart(required = false) List<MultipartFile> images,
            @RequestPart PostCreateRequest request, Authentication authentication) {

        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<PostResponse> response = postService.createPost(userId, images, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "게시물 단일 조회", description = "게시물 상세 정보를 조회합니다")
    @GetMapping("/{postId}")
    public ResponseEntity<BlobApiResponse<PostDetailResponse>> getPost(@PathVariable Long postId) {
        BlobApiResponse<PostDetailResponse> response = postService.getPost(postId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "게시물 삭제", description = "게시물을 삭제합니다")
    @DeleteMapping("/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> deletePost(@PathVariable Long postId,
            Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<Void> response = postService.deletePost(userId, postId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "피드 조회", description = "필터링된 게시물 피드를 조회합니다")
    @GetMapping("/feed")
    public ResponseEntity<BlobApiResponse<BlobPagedResponse<PostSummaryResponse>>> getFeed(
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "도시 위도") @RequestParam(required = false) Double cityLat,
            @Parameter(description = "도시 경도") @RequestParam(required = false) Double cityLng,
            @Parameter(description = "정렬 기준") @RequestParam(defaultValue = "NEWEST") String sortBy,
            @Parameter(description = "카테고리") @RequestParam(required = false) String categories,
            @Parameter(description = "시작 날짜") @RequestParam(required = false) String startDate,
            @Parameter(description = "종료 날짜") @RequestParam(required = false) String endDate,
            @Parameter(description = "이미지 포함 여부") @RequestParam(
                    defaultValue = "false") boolean hasImage,
            @Parameter(description = "위치 정보 포함 여부") @RequestParam(
                    defaultValue = "false") boolean hasLocation,
            @Parameter(description = "최소 좋아요 수") @RequestParam(defaultValue = "0") int minLikes,
            @Parameter(description = "검색 키워드") @RequestParam(required = false) String keyword) {

        BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> response =
                postService.getFeed(page, size, cityLat, cityLng, sortBy, categories, startDate,
                        endDate, hasImage, hasLocation, minLikes, keyword);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "지도용 마커 데이터", description = "지도 영역 내 게시물 마커 데이터를 조회합니다")
    @GetMapping("/map")
    public ResponseEntity<BlobApiResponse<List<MarkerDataResponse>>> getMarkers(
            @Parameter(description = "카테고리") @RequestParam(required = false) String categories,
            @Parameter(description = "최소 위도") @RequestParam double minLat,
            @Parameter(description = "최대 위도") @RequestParam double maxLat,
            @Parameter(description = "최소 경도") @RequestParam double minLng,
            @Parameter(description = "최대 경도") @RequestParam double maxLng) {

        BlobApiResponse<List<MarkerDataResponse>> response =
                postService.getMarkers(categories, minLat, maxLat, minLng, maxLng);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "좋아요 토글", description = "게시물 좋아요를 추가/제거합니다")
    @PostMapping("/like/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<LikeResponse>> togglePostLike(@PathVariable Long postId,
            Authentication authentication) {

        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<LikeResponse> response = postService.togglePostLike(userId, postId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "북마크 토글", description = "게시물 북마크를 추가/제거합니다")
    @PostMapping("/bookmark/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<BookmarkResponse>> toggleBookmark(
            @PathVariable Long postId, Authentication authentication) {

        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<BookmarkResponse> response = postService.toggleBookmark(userId, postId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "게시물 신고", description = "게시물을 신고합니다")
    @PostMapping("/report/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> reportPost(@PathVariable Long postId,
            @RequestBody ReportRequest request, Authentication authentication) {

        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<Void> response = postService.reportPost(userId, postId, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
