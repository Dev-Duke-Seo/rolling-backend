package com.blob.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequest {

    @Size(min = 2, max = 50, message = "닉네임은 2-50자 사이여야 합니다")
    private String nickname;

    private String email;
}
