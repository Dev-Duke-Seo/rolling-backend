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
public class ReplyDetailResponse {

    private Long replyId;
    private BlobUserResponse author;
    private String content;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReplyDetailResponse from(Comment reply) {
        return ReplyDetailResponse.builder().replyId(reply.getCommentId())
                .author(BlobUserResponse.from(reply.getUser())).content(reply.getContent())
                .likeCount(reply.getLikeCount()).createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt()).build();
    }
}
