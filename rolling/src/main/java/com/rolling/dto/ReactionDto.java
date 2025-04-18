package com.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionDto {
    private Long id;
    private Long recipientId;
    private String emoji;
    private Integer count;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionPreviewDto {
    private Long id;
    private String emoji;
    private Integer count;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionCreateDto {
    private String emoji;
    private String type; // "increase" or "decrease"
}
