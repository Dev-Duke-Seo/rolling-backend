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
public class MessageDto {
    private Long id;
    private Long recipientId;
    private String sender;
    private String profileImageURL;
    private String backgroundColor;
    private String relationship;
    private String content;
    private String font;
    private LocalDateTime createdAt;
}
