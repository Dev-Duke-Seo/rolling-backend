package com.blob.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostCreateRequest {

    @NotBlank(message = "제목은 필수입니다")
    @Size(max = 200, message = "제목은 200자 이하여야 합니다")
    private String title;

    @NotBlank(message = "내용은 필수입니다")
    private String content;

    @NotBlank(message = "카테고리는 필수입니다")
    @Size(max = 30, message = "카테고리는 30자 이하여야 합니다")
    private String category;

    @Size(max = 50, message = "서브카테고리는 50자 이하여야 합니다")
    private String subcategory;

    private Double latitude;

    private Double longitude;

    @Size(max = 500, message = "주소는 500자 이하여야 합니다")
    private String address;
}
