package com.rolling.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessagePreviewDto {
    private Long id;
    private String sender;
    private String content;
    private LocalDateTime createdAt;
    private String profileImageURL;
    private String relationship;
}
