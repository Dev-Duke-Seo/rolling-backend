package com.blob.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReplyRequest {

    @NotNull(message = "댓글 ID는 필수입니다")
    private Long parentCommentId;

    @NotBlank(message = "답글 내용은 필수입니다")
    private String content;
}
