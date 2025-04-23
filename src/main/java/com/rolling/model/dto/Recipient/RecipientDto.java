package com.rolling.model.dto.Recipient;

import java.time.LocalDateTime;
import java.util.List;

import com.rolling.model.dto.MessagePreviewDto;
import com.rolling.model.dto.ReactionPreviewDto;
import com.rolling.model.enums.ColorType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Recipient 정보 DTO")
public class RecipientDto {
    @Schema(description = "Recipient 고유 ID", example = "1")
    private Long id;

    @Schema(description = "Recipient 이름", example = "홍길동의 생일을 축하해주세요")
    private String name;

    @Schema(description = "배경색 (ENUM 타입)", example = "BLUE")
    private ColorType backgroundColor;

    @Schema(description = "배경 이미지 URL", example = "https://example.com/images/background.jpg",
            nullable = true)
    private String backgroundImageURL;

    @Schema(description = "생성 시간", example = "2023-06-15T14:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "전체 메시지 수", example = "15")
    private Integer messageCount;

    @Schema(description = "최근 메시지 목록 (최대 3개)")
    private List<MessagePreviewDto> recentMessages;

    @Schema(description = "전체 반응 수", example = "42")
    private Integer reactionCount;

    @Schema(description = "가장 많은 반응 목록 (최대 3개)")
    private List<ReactionPreviewDto> topReactions;
}

