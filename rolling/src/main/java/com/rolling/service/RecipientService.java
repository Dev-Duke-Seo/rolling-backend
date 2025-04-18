package com.rolling.service;

import com.rolling.dto.PageResponseDto;
import com.rolling.dto.RecipientDto;
import com.rolling.model.entity.Recipient;

public interface RecipientService {
    RecipientDto createRecipient(Recipient recipient);

    RecipientDto getRecipientById(Long id);

    PageResponseDto<RecipientDto> getAllRecipients(int limit, int offset);

    void deleteRecipient(Long id);
}
