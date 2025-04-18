package com.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientDto {
    private Long id;
    private String name;
    private String backgroundColor;
    private String backgroundImageURL;
    private LocalDateTime createdAt;
    private Integer messageCount;
    private List<MessagePreviewDto> recentMessages;
    private Integer reactionCount;
    private List<ReactionPreviewDto> topReactions;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class RecipientCreateDto {
    private String name;
    private String backgroundColor;
    private String backgroundImageURL;
}
