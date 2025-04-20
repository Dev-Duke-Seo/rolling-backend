package com.rolling.model.dto.Recipient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientCreateDto {
    private String name;
    private String backgroundColor;
    private String backgroundImageURL;
}
