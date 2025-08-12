package com.blob.service;

import com.blob.dto.request.CommentRequest;
import com.blob.dto.request.ReplyRequest;
import com.blob.dto.request.ReportRequest;
import com.blob.dto.response.*;

public interface CommentService {

    BlobApiResponse<CommentResponse> createComment(Long userId, CommentRequest request);

    BlobApiResponse<BlobPagedResponse<CommentDetailResponse>> getComments(Long postId, int page,
            int size);

    BlobApiResponse<Void> deleteComment(Long userId, Long commentId);

    BlobApiResponse<LikeResponse> toggleCommentLike(Long userId, Long commentId);

    BlobApiResponse<Void> reportComment(Long userId, Long commentId, ReportRequest request);

    BlobApiResponse<ReplyResponse> createReply(Long userId, ReplyRequest request);

    BlobApiResponse<BlobPagedResponse<ReplyDetailResponse>> getReplies(Long commentId, int page,
            int size);
}
