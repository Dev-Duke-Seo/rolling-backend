package com.rolling.dto;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageCreateDto {
    private Long recipientId;
    private String sender;
    private String profileImageURL;
    private String backgroundColor;
    private String relationship;
    private String content;
    private String font;
}

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
