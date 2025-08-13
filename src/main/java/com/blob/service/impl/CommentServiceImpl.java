package com.blob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blob.dto.request.CommentRequest;
import com.blob.dto.request.ReplyRequest;
import com.blob.dto.request.ReportRequest;
import com.blob.dto.response.*;
import com.blob.entity.*;
import com.blob.repository.*;
import com.blob.service.CommentService;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final BlobUserRepository userRepository;
    private final LikeRepository likeRepository;
    private final ReportRepository reportRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public BlobApiResponse<CommentResponse> createComment(Long userId, Long postId,
            CommentRequest request) {
        try {
            // 사용자 조회
            BlobUser user = userRepository.findById(userId).orElse(null);
            if (user == null || user.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다.",
                        "USER_NOT_FOUND");
            }

            // 게시물 조회
            Post post = postRepository.findById(postId).orElse(null);
            if (post == null || post.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "게시물을 찾을 수 없습니다.",
                        "POST_NOT_FOUND");
            }

            // 댓글 생성
            Comment comment = Comment.builder().post(post).user(user).content(request.getContent())
                    .likeCount(0).build();

            Comment savedComment = commentRepository.save(comment);

            // 게시물 댓글 수 증가
            post.incrementCommentCount();
            postRepository.save(post);

            // 사용자 댓글 수 증가
            user.incrementCommentCount();
            userRepository.save(user);

            // 게시물 작성자에게 알림 (자신이 아닌 경우)
            if (!post.getUser().getUserId().equals(userId)) {
                createNotification(post.getUser().getUserId(), postId, "COMMENT",
                        user.getNickname() + "님이 회원님의 게시물에 댓글을 남겼습니다.");
            }

            CommentResponse response = CommentResponse.from(savedComment);
            return BlobApiResponse.success(response);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "댓글 작성 중 오류가 발생했습니다.", "COMMENT_CREATE_ERROR");
        }
    }

    @Override
    public BlobApiResponse<BlobPagedResponse<CommentDetailResponse>> getCommentsByPost(Long postId,
            int page, int size) {
        try {
            // 게시물 존재 확인
            if (!postRepository.existsById(postId)) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "게시물을 찾을 수 없습니다.",
                        "POST_NOT_FOUND");
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
            Page<Comment> comments = commentRepository
                    .findByPostPostIdAndParentCommentIsNullAndIsDeletedFalse(postId, pageable);

            BlobPagedResponse<CommentDetailResponse> response =
                    BlobPagedResponse.<CommentDetailResponse>builder()
                            .content(comments.getContent().stream().map(CommentDetailResponse::from)
                                    .toList())
                            .totalElements(comments.getTotalElements())
                            .totalPages(comments.getTotalPages()).size(comments.getSize())
                            .number(comments.getNumber()).first(comments.isFirst())
                            .last(comments.isLast()).empty(comments.isEmpty()).build();

            return BlobApiResponse.success(response);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "댓글 조회 중 오류가 발생했습니다.", "COMMENT_FETCH_ERROR");
        }
    }

    @Override
    @Transactional
    public BlobApiResponse<Void> deleteComment(Long userId, Long commentId) {
        try {
            Comment comment = commentRepository.findById(commentId).orElse(null);
            if (comment == null || comment.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "댓글을 찾을 수 없습니다.",
                        "COMMENT_NOT_FOUND");
            }

            // 작성자 권한 확인
            if (!comment.getUser().getUserId().equals(userId)) {
                return BlobApiResponse.error(HttpStatus.FORBIDDEN.value(), "댓글을 삭제할 권한이 없습니다.",
                        "FORBIDDEN");
            }

            // 소프트 삭제
            comment.setIsDeleted(true);
            commentRepository.save(comment);

            // 게시물 댓글 수 감소
            Post post = comment.getPost();
            post.decrementCommentCount();
            postRepository.save(post);

            // 사용자 댓글 수 감소
            BlobUser user = comment.getUser();
            user.decrementCommentCount();
            userRepository.save(user);

            return BlobApiResponse.success(null);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "댓글 삭제 중 오류가 발생했습니다.", "COMMENT_DELETE_ERROR");
        }
    }

    @Override
    @Transactional
    public BlobApiResponse<ReplyResponse> createReply(Long userId, Long commentId,
            ReplyRequest request) {
        try {
            // 사용자 조회
            BlobUser user = userRepository.findById(userId).orElse(null);
            if (user == null || user.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다.",
                        "USER_NOT_FOUND");
            }

            // 부모 댓글 조회
            Comment parentComment = commentRepository.findById(commentId).orElse(null);
            if (parentComment == null || parentComment.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "댓글을 찾을 수 없습니다.",
                        "COMMENT_NOT_FOUND");
            }

            // 대댓글 생성
            Comment reply = Comment.builder().post(parentComment.getPost()).user(user)
                    .parentComment(parentComment).content(request.getContent()).likeCount(0)
                    .build();

            Comment savedReply = commentRepository.save(reply);

            // 게시물 댓글 수 증가
            Post post = parentComment.getPost();
            post.incrementCommentCount();
            postRepository.save(post);

            // 사용자 댓글 수 증가
            user.incrementCommentCount();
            userRepository.save(user);

            // 부모 댓글 작성자에게 알림 (자신이 아닌 경우)
            if (!parentComment.getUser().getUserId().equals(userId)) {
                createNotification(parentComment.getUser().getUserId(), post.getPostId(), "REPLY",
                        user.getNickname() + "님이 회원님의 댓글에 답글을 남겼습니다.");
            }

            ReplyResponse response = ReplyResponse.from(savedReply);
            return BlobApiResponse.success(response);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "대댓글 작성 중 오류가 발생했습니다.", "REPLY_CREATE_ERROR");
        }
    }

    @Override
    public BlobApiResponse<BlobPagedResponse<ReplyDetailResponse>> getRepliesByComment(
            Long commentId, int page, int size) {
        try {
            // 부모 댓글 존재 확인
            if (!commentRepository.existsById(commentId)) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "댓글을 찾을 수 없습니다.",
                        "COMMENT_NOT_FOUND");
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
            Page<Comment> replies = commentRepository
                    .findByParentCommentCommentIdAndIsDeletedFalse(commentId, pageable);

            BlobPagedResponse<ReplyDetailResponse> response =
                    BlobPagedResponse.from(replies.map(ReplyDetailResponse::from));

            return BlobApiResponse.success(response);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "대댓글 조회 중 오류가 발생했습니다.", "REPLY_FETCH_ERROR");
        }
    }

    @Override
    @Transactional
    public BlobApiResponse<LikeResponse> toggleCommentLike(Long userId, Long commentId) {
        try {
            // 사용자 조회
            BlobUser user = userRepository.findById(userId).orElse(null);
            if (user == null || user.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다.",
                        "USER_NOT_FOUND");
            }

            // 댓글 조회
            Comment comment = commentRepository.findById(commentId).orElse(null);
            if (comment == null || comment.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "댓글을 찾을 수 없습니다.",
                        "COMMENT_NOT_FOUND");
            }

            // 기존 좋아요 확인
            Optional<Like> existingLike = likeRepository.findByUserUserIdAndTargetTypeAndTargetId(
                    userId, Like.LikeTargetType.COMMENT, commentId);

            boolean isLiked;
            if (existingLike.isPresent()) {
                // 좋아요 취소
                likeRepository.delete(existingLike.get());
                comment.decrementLikeCount();
                isLiked = false;
            } else {
                // 좋아요 추가
                Like like = Like.builder().user(user).targetType(Like.LikeTargetType.COMMENT)
                        .targetId(commentId).build();
                likeRepository.save(like);
                comment.incrementLikeCount();
                isLiked = true;

                // 댓글 작성자에게 알림 (자신이 아닌 경우)
                if (!comment.getUser().getUserId().equals(userId)) {
                    createNotification(comment.getUser().getUserId(), comment.getPost().getPostId(),
                            "COMMENT_LIKE", user.getNickname() + "님이 회원님의 댓글을 좋아합니다.");
                }
            }

            commentRepository.save(comment);

            LikeResponse response =
                    LikeResponse.builder().liked(isLiked).likeCount(comment.getLikeCount()).build();

            return BlobApiResponse.success(response);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "댓글 좋아요 처리 중 오류가 발생했습니다.", "COMMENT_LIKE_ERROR");
        }
    }

    @Override
    @Transactional
    public BlobApiResponse<Void> reportComment(Long userId, Long commentId, ReportRequest request) {
        try {
            // 사용자 조회
            BlobUser user = userRepository.findById(userId).orElse(null);
            if (user == null || user.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다.",
                        "USER_NOT_FOUND");
            }

            // 댓글 조회
            Comment comment = commentRepository.findById(commentId).orElse(null);
            if (comment == null || comment.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), "댓글을 찾을 수 없습니다.",
                        "COMMENT_NOT_FOUND");
            }

            // 이미 신고한 경우 확인
            boolean alreadyReported = reportRepository.existsByReporterUserIdAndTargetTypeAndTargetId(
                    userId, Report.ReportTargetType.COMMENT, commentId);
            if (alreadyReported) {
                return BlobApiResponse.error(HttpStatus.CONFLICT.value(), "이미 신고한 댓글입니다.",
                        "ALREADY_REPORTED");
            }

            // 신고 생성
            Report report =
                    Report.builder().reporter(user).targetType(Report.ReportTargetType.COMMENT)
                            .targetId(commentId).reason(request.getReason()).build();

            reportRepository.save(report);

            return BlobApiResponse.success(null);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "댓글 신고 처리 중 오류가 발생했습니다.", "COMMENT_REPORT_ERROR");
        }
    }

    private void createNotification(Long userId, Long postId, String type, String message) {
        try {
            BlobUser user = userRepository.findById(userId).orElse(null);
            if (user != null && !user.getIsDeleted()) {
                Notification notification = Notification.builder().user(user).relatedId(postId)
                        .type(getNotificationType(type)).title(getNotificationTitle(type))
                        .content(message).isRead(false).build();
                notificationRepository.save(notification);
            }
        } catch (Exception e) {
            // 알림 생성 실패는 메인 로직에 영향을 주지 않음
        }
    }

    private Notification.NotificationType getNotificationType(String type) {
        return switch (type) {
            case "COMMENT" -> Notification.NotificationType.NEW_COMMENT;
            case "REPLY" -> Notification.NotificationType.NEW_REPLY;
            case "COMMENT_LIKE" -> Notification.NotificationType.COMMENT_LIKE;
            default -> Notification.NotificationType.SYSTEM_NOTICE;
        };
    }

    private String getNotificationTitle(String type) {
        return switch (type) {
            case "COMMENT" -> "새 댓글 알림";
            case "REPLY" -> "새 답글 알림";
            case "COMMENT_LIKE" -> "댓글 좋아요 알림";
            default -> "시스템 알림";
        };
    }
}
