package com.blob.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBlobUserRequest {

    @NotBlank(message = "Blob ID는 필수입니다")
    @Size(min = 3, max = 50, message = "Blob ID는 3-50자 사이여야 합니다")
    private String blobId;

    @NotBlank(message = "닉네임은 필수입니다")
    @Size(min = 2, max = 50, message = "닉네임은 2-50자 사이여야 합니다")
    private String nickname;

    private String email;
}
