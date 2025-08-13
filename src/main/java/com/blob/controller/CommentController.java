package com.blob.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.blob.dto.request.CommentRequest;
import com.blob.dto.request.ReplyRequest;
import com.blob.dto.request.ReportRequest;
import com.blob.dto.response.*;
import com.blob.service.CommentService;

@RestController
@RequestMapping("/blob/comment")
@RequiredArgsConstructor
@Tag(name = "BLOB Comment", description = "BLOB 댓글 관련 API")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성", description = "게시글에 새 댓글을 작성합니다")
    @PostMapping("/post/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<CommentResponse>> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request,
            Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<CommentResponse> response = commentService.createComment(userId, postId, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "댓글 목록 조회", description = "게시글의 댓글 목록을 조회합니다")
    @GetMapping("/post/{postId}")
    public ResponseEntity<BlobApiResponse<BlobPagedResponse<CommentDetailResponse>>> getCommentsByPost(
            @PathVariable Long postId,
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {
        
        BlobApiResponse<BlobPagedResponse<CommentDetailResponse>> response = 
                commentService.getCommentsByPost(postId, page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다")
    @DeleteMapping("/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> deleteComment(
            @PathVariable Long commentId,
            Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<Void> response = commentService.deleteComment(userId, commentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "대댓글 작성", description = "댓글에 대댓글을 작성합니다")
    @PostMapping("/reply/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<ReplyResponse>> createReply(
            @PathVariable Long commentId,
            @RequestBody ReplyRequest request,
            Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<ReplyResponse> response = commentService.createReply(userId, commentId, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "대댓글 목록 조회", description = "댓글의 대댓글 목록을 조회합니다")
    @GetMapping("/reply/{commentId}")
    public ResponseEntity<BlobApiResponse<BlobPagedResponse<ReplyDetailResponse>>> getRepliesByComment(
            @PathVariable Long commentId,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page) {
        
        BlobApiResponse<BlobPagedResponse<ReplyDetailResponse>> response = 
                commentService.getRepliesByComment(commentId, page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "댓글 좋아요 토글", description = "댓글 좋아요를 추가/제거합니다")
    @PostMapping("/like/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<LikeResponse>> toggleCommentLike(
            @PathVariable Long commentId,
            Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<LikeResponse> response = commentService.toggleCommentLike(userId, commentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "댓글 신고", description = "댓글을 신고합니다")
    @PostMapping("/report/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> reportComment(
            @PathVariable Long commentId,
            @RequestBody ReportRequest request,
            Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<Void> response = commentService.reportComment(userId, commentId, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}