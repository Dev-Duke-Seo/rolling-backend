package com.rolling.model.dto;

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
