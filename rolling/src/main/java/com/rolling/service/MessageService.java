package com.rolling.service;

import com.rolling.dto.MessageDto;
import com.rolling.dto.PageResponseDto;
import com.rolling.model.entity.Message;

public interface MessageService {
    MessageDto createMessage(Message message);

    MessageDto getMessageById(Long id);

    PageResponseDto<MessageDto> getMessagesByRecipientId(Long recipientId, int limit, int offset);

    MessageDto updateMessage(Long id, Message messageDetails);

    void deleteMessage(Long id);
}
