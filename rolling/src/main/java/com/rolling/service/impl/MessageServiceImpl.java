package com.rolling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rolling.dto.MessageDto;
import com.rolling.dto.PageResponseDto;
import com.rolling.exception.ResourceNotFoundException;
import com.rolling.model.entity.Message;
import com.rolling.repository.MessageRepository;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final RecipientRepository recipientRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, RecipientRepository recipientRepository) {
        this.messageRepository = messageRepository;
        this.recipientRepository = recipientRepository;
    }

    @Override
    public MessageDto createMessage(Message message) {
        Message savedMessage = messageRepository.save(message);
        return convertToDto(savedMessage);
    }

    @Override
    public MessageDto getMessageById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
        return convertToDto(message);
    }

    @Override
    public PageResponseDto<MessageDto> getMessagesByRecipientId(Long recipientId, int limit, int offset) {
        // 순환 참조가 없는지 확인
        if (!recipientRepository.existsById(recipientId)) {
            throw new ResourceNotFoundException("Recipient not found with id: " + recipientId);
        }

        Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Message> messagesPage = messageRepository.findByRecipientId(recipientId, pageable);

        List<MessageDto> messages = messagesPage.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        String nextUrl = null;
        String prevUrl = null;

        if (messagesPage.hasNext()) {
            nextUrl = "/recipients/" + recipientId + "/messages/?limit=" + limit + "&offset=" + (offset + limit);
        }

        if (offset > 0) {
            prevUrl = "/recipients/" + recipientId + "/messages/?limit=" + limit + "&offset="
                    + Math.max(0, offset - limit);
        }

        return PageResponseDto.<MessageDto>builder()
                .count((int) messagesPage.getTotalElements())
                .next(nextUrl)
                .previous(prevUrl)
                .results(messages)
                .build();
    }

    @Override
    public MessageDto updateMessage(Long id, Message messageDetails) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));

        message.setSender(messageDetails.getSender());
        message.setProfileImageURL(messageDetails.getProfileImageURL());
        message.setBackgroundColor(messageDetails.getBackgroundColor());
        message.setRelationship(messageDetails.getRelationship());
        message.setContent(messageDetails.getContent());
        message.setFont(messageDetails.getFont());

        Message updatedMessage = messageRepository.save(message);
        return convertToDto(updatedMessage);
    }

    @Override
    public void deleteMessage(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
        messageRepository.delete(message);
    }

    private MessageDto convertToDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .recipientId(message.getRecipient().getId())
                .sender(message.getSender())
                .profileImageURL(message.getProfileImageURL())
                .backgroundColor(message.getBackgroundColor())
                .relationship(message.getRelationship())
                .content(message.getContent())
                .font(message.getFont())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
