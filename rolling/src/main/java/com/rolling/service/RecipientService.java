package com.rolling.service;

import com.rolling.model.ServiceResult;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.dto.Recipient.RecipientDto;

public interface RecipientService {
    ServiceResult<RecipientDto> createRecipient(RecipientCreateDto recipient);

    ServiceResult<RecipientDto> getRecipientById(Long id);

    ServiceResult<PageResponseDto<RecipientDto>> getAllRecipients(int limit, int offset);

    ServiceResult<Void> deleteRecipient(Long id);

}
