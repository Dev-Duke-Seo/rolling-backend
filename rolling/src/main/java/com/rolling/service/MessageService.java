package com.rolling.service;

import com.rolling.model.ServiceResult;
import com.rolling.model.dto.MessageDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.entity.Message;

public interface MessageService {
    ServiceResult<MessageDto> createMessage(Message message, Long recipientId);

    ServiceResult<MessageDto> getMessageById(Long id);

    ServiceResult<PageResponseDto<MessageDto>> getMessagesByRecipientId(Long recipientId, int limit,
            int offset);

    ServiceResult<MessageDto> updateMessage(Long id, Message messageDetails);

    ServiceResult<Void> deleteMessage(Long id);
}
