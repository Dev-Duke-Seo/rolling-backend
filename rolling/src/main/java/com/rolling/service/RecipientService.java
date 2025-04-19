package com.rolling.service;

import com.rolling.dto.PageResponseDto;
import com.rolling.dto.RecipientDto;
import com.rolling.model.ServiceResult;
import com.rolling.model.entity.Recipient;

public interface RecipientService {
    ServiceResult<RecipientDto> createRecipient(Recipient recipient);

    ServiceResult<RecipientDto> getRecipientById(Long id);

    ServiceResult<PageResponseDto<RecipientDto>> getAllRecipients(int limit, int offset);

    ServiceResult<Void> deleteRecipient(Long id);
}
