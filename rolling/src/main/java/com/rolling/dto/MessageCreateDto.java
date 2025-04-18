package com.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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