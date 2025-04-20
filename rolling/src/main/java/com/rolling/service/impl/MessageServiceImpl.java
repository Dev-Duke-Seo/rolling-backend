package com.rolling.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.rolling.exception.ResourceNotFoundException;
import com.rolling.model.ServiceResult;
import com.rolling.model.dto.MessageDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.entity.Message;
import com.rolling.model.entity.Recipient;
import com.rolling.repository.MessageRepository;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.MessageService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final RecipientRepository recipientRepository;

    @Override
    public ServiceResult<MessageDto> createMessage(Message message, Long recipientId) {
        Recipient recipient = recipientRepository.findById(recipientId).orElseThrow(
                () -> new ResourceNotFoundException("Recipient not found with id: " + recipientId));
        message.setRecipient(recipient);
        Message savedMessage = messageRepository.save(message);
        return ServiceResult.success(convertToDto(savedMessage));
    }

    @Override
    public ServiceResult<MessageDto> getMessageById(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Message not found with id: " + id));
        return ServiceResult.success(convertToDto(message));
    }

    @Override
    public PageResponseDto<MessageDto> getMessagesByRecipientId(Long recipientId, int limit,
            int offset) {
        // 순환 참조가 없는지 확인
        if (!recipientRepository.existsById(recipientId)) {
            throw new ResourceNotFoundException("Recipient not found with id: " + recipientId);
        }

        Pageable pageable =
                PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Message> messagesPage = messageRepository.findByRecipientId(recipientId, pageable);

        List<MessageDto> messages = messagesPage.getContent().stream().map(this::convertToDto)
                .collect(Collectors.toList());

        String nextUrl = null;
        String prevUrl = null;

        if (messagesPage.hasNext()) {
            nextUrl = "/recipients/" + recipientId + "/messages/?limit=" + limit + "&offset="
                    + (offset + limit);
        }

        if (offset > 0) {
            prevUrl = "/recipients/" + recipientId + "/messages/?limit=" + limit + "&offset="
                    + Math.max(0, offset - limit);
        }

        return PageResponseDto.<MessageDto>builder().count((int) messagesPage.getTotalElements())
                .next(nextUrl).previous(prevUrl).results(messages).build();
    }

    @Override
    public ServiceResult<MessageDto> updateMessage(Long id, Message messageDetails) {
        Message message = messageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Message not found with id: " + id));

        message.setSender(messageDetails.getSender());
        message.setProfileImageURL(messageDetails.getProfileImageURL());
        message.setBackgroundColor(messageDetails.getBackgroundColor());
        message.setRelationship(messageDetails.getRelationship());
        message.setContent(messageDetails.getContent());
        message.setFont(messageDetails.getFont());

        Message updatedMessage = messageRepository.save(message);
        return ServiceResult.success(convertToDto(updatedMessage));
    }

    @Override
    public ServiceResult<Void> deleteMessage(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Message not found with id: " + id));
        messageRepository.delete(message);
        return ServiceResult.success(null);
    }

    private MessageDto convertToDto(Message message) {
        return MessageDto.builder().id(message.getId()).recipientId(message.getRecipient().getId())
                .sender(message.getSender()).profileImageURL(message.getProfileImageURL())
                .backgroundColor(message.getBackgroundColor())
                .relationship(message.getRelationship()).content(message.getContent())
                .font(message.getFont()).createdAt(message.getCreatedAt()).build();
    }
}
