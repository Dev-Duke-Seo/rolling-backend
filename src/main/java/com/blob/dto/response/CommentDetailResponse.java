package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.blob.entity.Comment;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailResponse {

    private Long commentId;
    private BlobUserResponse author;
    private String content;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentDetailResponse from(Comment comment) {
        return CommentDetailResponse.builder().commentId(comment.getCommentId())
                .author(BlobUserResponse.from(comment.getUser())).content(comment.getContent())
                .likeCount(comment.getLikeCount()).createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt()).build();
    }
}
