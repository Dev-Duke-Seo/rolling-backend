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
import com.blob.dto.request.CreateBlobUserRequest;
import com.blob.dto.request.UpdateProfileRequest;
import com.blob.dto.response.*;
import com.blob.service.BlobUserService;

@RestController
@RequestMapping("/blob/users")
@RequiredArgsConstructor
@Tag(name = "BLOB User", description = "BLOB 사용자 관련 API")
public class BlobUserController {

    private final BlobUserService blobUserService;

    @Operation(summary = "사용자 생성", description = "새로운 BLOB 사용자를 생성합니다")
    @PostMapping
    public ResponseEntity<BlobApiResponse<BlobUserResponse>> createUser(
            @RequestBody CreateBlobUserRequest request) {
        BlobApiResponse<BlobUserResponse> response = blobUserService.createUser(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "사용자 상세 조회", description = "Blob ID로 사용자 정보를 조회합니다")
    @GetMapping("/{blobId}")
    public ResponseEntity<BlobApiResponse<BlobUserResponse>> getUserDetail(
            @PathVariable String blobId) {
        BlobApiResponse<BlobUserResponse> response = blobUserService.getUserDetail(blobId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "사용자 삭제", description = "사용자를 삭제합니다")
    @DeleteMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        BlobApiResponse<Void> response = blobUserService.deleteUser(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Blob ID 중복 확인", description = "Blob ID가 사용 가능한지 확인합니다")
    @GetMapping("/check/{blobId}")
    public ResponseEntity<BlobApiResponse<Boolean>> checkBlobId(@PathVariable String blobId) {
        BlobApiResponse<Boolean> response = blobUserService.checkBlobId(blobId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "프로필 업데이트", description = "사용자 프로필을 업데이트합니다")
    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<BlobUserResponse>> updateProfile(
            @RequestPart(required = false) MultipartFile profileImage,
            @RequestPart UpdateProfileRequest request, Authentication authentication) {

        Long userId = Long.parseLong(authentication.getName()); // JWT에서 userId 추출
        BlobApiResponse<BlobUserResponse> response =
                blobUserService.updateProfile(userId, profileImage, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "프로필 이미지 삭제", description = "사용자 프로필 이미지를 삭제합니다")
    @DeleteMapping("/profile/image")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> deleteProfileImage(Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<Void> response = blobUserService.deleteProfileImage(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "사용자 게시물 목록", description = "사용자가 작성한 게시물 목록을 조회합니다")
    @GetMapping("/{blobId}/posts")
    public ResponseEntity<BlobApiResponse<BlobPagedResponse<PostSummaryResponse>>> getUserPosts(
            @PathVariable String blobId,
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> response =
                blobUserService.getUserPosts(blobId, page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "북마크 목록", description = "사용자가 북마크한 게시물 목록을 조회합니다")
    @GetMapping("/{blobId}/bookmarks")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<BlobPagedResponse<PostSummaryResponse>>> getUserBookmarks(
            @PathVariable String blobId,
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {

        BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> response =
                blobUserService.getUserBookmarks(blobId, page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
