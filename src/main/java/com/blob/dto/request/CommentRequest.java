package com.blob.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

    @NotNull(message = "게시물 ID는 필수입니다")
    private Long postId;

    @NotBlank(message = "댓글 내용은 필수입니다")
    private String content;
}
