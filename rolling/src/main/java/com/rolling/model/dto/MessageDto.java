package com.rolling.model.dto;

import java.time.LocalDateTime;

import com.rolling.model.enums.ColorType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "메시지 정보 DTO")
public class MessageDto {
    @Schema(description = "메시지 고유 ID", example = "1")
    private Long id;

    @Schema(description = "수신자(Recipient) ID", example = "42")
    private Long recipientId;

    @Schema(description = "발신자 이름", example = "김철수")
    private String sender;

    @Schema(description = "발신자 프로필 이미지 URL", example = "https://example.com/images/profile.jpg",
            nullable = true)
    private String profileImageURL;

    @Schema(description = "배경색", example = "BLUE")
    private ColorType backgroundColor;

    @Schema(description = "발신자와 수신자의 관계", example = "동료")
    private String relationship;

    @Schema(description = "메시지 내용", example = "생일 축하합니다! 행복한 하루 되세요.")
    private String content;

    @Schema(description = "폰트 종류", example = "Pretendard")
    private String font;

    @Schema(description = "생성 시간", example = "2023-06-15T14:30:00")
    private LocalDateTime createdAt;
}
