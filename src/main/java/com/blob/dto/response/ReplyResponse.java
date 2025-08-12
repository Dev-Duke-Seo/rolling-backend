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
public class ReplyResponse {

    private Long replyId;
    private Long parentCommentId;
    private String content;
    private LocalDateTime createdAt;

    public static ReplyResponse from(Comment reply) {
        return ReplyResponse.builder().replyId(reply.getCommentId())
                .parentCommentId(reply.getParentComment().getCommentId())
                .content(reply.getContent()).createdAt(reply.getCreatedAt()).build();
    }
}
