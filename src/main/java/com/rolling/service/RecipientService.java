package com.rolling.service;

import com.common.dto.ServiceResult;
import com.common.dto.PageResponseDto;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.dto.Recipient.RecipientDto;

public interface RecipientService {
    ServiceResult<RecipientDto> createRecipient(RecipientCreateDto recipient);

    ServiceResult<RecipientDto> getRecipientById(Long id);

    ServiceResult<PageResponseDto<RecipientDto>> getAllRecipients(int limit, int offset, String sort);

    ServiceResult<Void> deleteRecipient(Long id);

}
