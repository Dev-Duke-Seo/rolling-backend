package com.rolling.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.common.exception.ServiceError;
import com.common.dto.ServiceResult;
import com.rolling.model.dto.MessageDto;
import com.common.dto.PageResponseDto;
import com.rolling.model.entity.Message;
import com.rolling.model.entity.Recipient;
import com.rolling.model.enums.ColorType;
import com.rolling.repository.MessageRepository;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.MessageService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

        private final MessageRepository messageRepository;
        private final RecipientRepository recipientRepository;

        //
        @Override
        @Transactional
        public ServiceResult<MessageDto> createMessage(Message message, Long recipientId) {
                Recipient recipient = recipientRepository.findById(recipientId)
                                .orElseThrow(() -> ServiceError.recipientNotFound(recipientId));
                message.setRecipient(recipient);
                Message savedMessage = messageRepository.save(message);
                return ServiceResult.success(convertToDto(savedMessage));
        }

        //
        @Override
        @Transactional(readOnly = true)
        public ServiceResult<MessageDto> getMessageById(Long id) {
                Message message = messageRepository.findById(id)
                                .orElseThrow(() -> ServiceError.messageNotFound(id));
                return ServiceResult.success(convertToDto(message));
        }

        //
        @Override
        @Transactional(readOnly = true)
        public ServiceResult<PageResponseDto<MessageDto>> getMessagesByRecipientId(Long recipientId,
                        int limit, int offset) {
                // 순환 참조가 없는지 확인
                if (!recipientRepository.existsById(recipientId)) {
                        return ServiceResult.<PageResponseDto<MessageDto>>builder().isSuccess(false)
                                        .message("수신자(id: " + recipientId + ")를 찾을 수 없습니다")
                                        .status(HttpStatus.NOT_FOUND).build();
                }
                //
                Pageable pageable = PageRequest.of(offset / limit, limit,
                                Sort.by(Sort.Direction.DESC, "createdAt"));
                Page<Message> messagesPage =
                                messageRepository.findByRecipientId(recipientId, pageable);
                //
                List<MessageDto> messages = messagesPage.getContent().stream()
                                .map(this::convertToDto).collect(Collectors.toList());
                //
                String nextUrl = null;
                String prevUrl = null;
                //
                if (messagesPage.hasNext()) {
                        nextUrl = "/recipients/" + recipientId + "/messages/?limit=" + limit
                                        + "&offset=" + (offset + limit);
                }
                //
                if (offset > 0) {
                        prevUrl = "/recipients/" + recipientId + "/messages/?limit=" + limit
                                        + "&offset=" + Math.max(0, offset - limit);
                }
                //
                return ServiceResult.success(PageResponseDto.<MessageDto>builder()
                                .count((int) messagesPage.getTotalElements()).next(nextUrl)
                                .previous(prevUrl).results(messages).build());
        }

        //
        @Override
        @Transactional
        public ServiceResult<MessageDto> updateMessage(Long id, Message messageDetails) {
                Optional<Message> messageOpt = messageRepository.findById(id);
                if (messageOpt.isEmpty()) {
                        return ServiceResult.<MessageDto>builder().isSuccess(false)
                                        .message("메시지(id: " + id + ")를 찾을 수 없습니다")
                                        .status(HttpStatus.NOT_FOUND).build();
                }

                Message message = messageOpt.get();
                //
                message.setSender(messageDetails.getSender());
                message.setProfileImageURL(messageDetails.getProfileImageURL());
                message.setBackgroundColor(messageDetails.getBackgroundColor());
                message.setRelationship(messageDetails.getRelationship());
                message.setContent(messageDetails.getContent());
                message.setFont(messageDetails.getFont());
                //
                Message updatedMessage = messageRepository.save(message);
                return ServiceResult.success(convertToDto(updatedMessage));
        }

        //
        @Override
        @Transactional
        public ServiceResult<Void> deleteMessage(Long id) {
                Optional<Message> messageOpt = messageRepository.findById(id);
                if (messageOpt.isEmpty()) {
                        return ServiceResult.<Void>builder().isSuccess(false)
                                        .message("메시지(id: " + id + ")를 찾을 수 없습니다")
                                        .status(HttpStatus.NOT_FOUND).build();
                }

                messageRepository.delete(messageOpt.get());
                return ServiceResult.success("message deleted");
        }

        //
        private MessageDto convertToDto(Message message) {
                return MessageDto.builder().id(message.getId())
                                .recipientId(message.getRecipient().getId())
                                .sender(message.getSender())
                                .profileImageURL(message.getProfileImageURL())
                                .backgroundColor(ColorType.fromValue(message.getBackgroundColor()))
                                .relationship(message.getRelationship().getValue())
                                .content(message.getContent()).font(message.getFont())
                                .createdAt(message.getCreatedAt()).build();
        }
}
