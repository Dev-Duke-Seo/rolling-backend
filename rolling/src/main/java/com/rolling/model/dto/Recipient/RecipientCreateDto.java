package com.rolling.model.dto.Recipient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Recipient 생성 요청 DTO")
public class RecipientCreateDto {
    @Schema(description = "Recipient 이름", example = "홍길동의 생일을 축하해주세요", required = true)
    private String name;

    @Schema(description = "배경색 코드", example = "blue", required = true,
            allowableValues = {"beige", "purple", "blue", "green"})
    private String backgroundColor;

    @Schema(description = "배경 이미지 URL", example = "https://example.com/images/background.jpg",
            nullable = true)
    private String backgroundImageURL;
}
