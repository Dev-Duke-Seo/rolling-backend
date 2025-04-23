package com.rolling.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "메시지 생성 요청 DTO")
public class MessageCreateDto {
    @Schema(description = "발신자 이름", example = "김철수", required = true)
    private Long recipientId;

    @Schema(description = "발신자 프로필 이미지 URL", example = "https://example.com/images/profile.jpg",
            nullable = true)
    private String sender;

    @Schema(description = "배경색 코드", example = "blue", required = true,
            allowableValues = {"beige", "purple", "blue", "green"})
    private String profileImageURL;

    @Schema(description = "배경색 코드", example = "blue", required = true,
            allowableValues = {"beige", "purple", "blue", "green"})
    private String backgroundColor;

    @Schema(description = "발신자와 수신자의 관계", example = "동료", required = true)
    private String relationship;

    @Schema(description = "메시지 내용", example = "생일 축하합니다! 행복한 하루 되세요.", required = true)
    private String content;

    @Schema(description = "폰트 종류", example = "Pretendard", required = true,
            allowableValues = {"Noto Sans", "Pretendard", "나눔명조", "나눔손글씨 손편지체"})
    private String font;
}
