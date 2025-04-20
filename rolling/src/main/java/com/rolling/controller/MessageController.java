package com.rolling.controller;

import com.rolling.service.MessageService;
import lombok.RequiredArgsConstructor;
import com.rolling.exception.ResourceNotFoundException;
import com.rolling.model.ServiceResult;
import com.rolling.model.dto.MessageDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.entity.Message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me/{recipientId}/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/")
    public ResponseEntity<MessageDto> createMessage(@PathVariable Long recipientId,
            @RequestBody Message messageRequest) {

        ServiceResult<MessageDto> createdMessage = messageService.createMessage(messageRequest);
        return new ResponseEntity<>(createdMessage.getDataOrNull(), HttpStatus.CREATED);
    }

    @GetMapping("/{messageId}/")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long recipientId,
            @PathVariable Long messageId) {

        ServiceResult<MessageDto> message = messageService.getMessageById(messageId);

        if (!message.getDataOrNull().getRecipientId().equals(recipientId)) {
            throw new ResourceNotFoundException(
                    "Message not found with id " + messageId + " for recipient id " + recipientId);
        }

        return ResponseEntity.ok(message.getDataOrNull());
    }

    @GetMapping("/")
    public ResponseEntity<PageResponseDto<MessageDto>> getMessagesByRecipientId(
            @PathVariable Long recipientId, @RequestParam(defaultValue = "8") int limit,
            @RequestParam(defaultValue = "0") int offset) {

        PageResponseDto<MessageDto> messages =
                messageService.getMessagesByRecipientId(recipientId, limit, offset);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{messageId}/")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable Long recipientId,
            @PathVariable Long messageId, @RequestBody Message messageDetails) {

        ServiceResult<MessageDto> existingMessage = messageService.getMessageById(messageId);

        if (!existingMessage.getDataOrNull().getRecipientId().equals(recipientId)) {
            throw new ResourceNotFoundException(
                    "Message not found with id " + messageId + " for recipient id " + recipientId);
        }

        ServiceResult<MessageDto> updatedMessage =
                messageService.updateMessage(messageId, messageDetails);
        return ResponseEntity.ok(updatedMessage.getDataOrNull());
    }

    @PatchMapping("/{messageId}/")
    public ResponseEntity<MessageDto> partialUpdateMessage(@PathVariable Long recipientId,
            @PathVariable Long messageId, @RequestBody Message messageDetails) {

        // PATCH는 부분 업데이트를 위한 것이지만, 현재는 PUT과 동일하게 처리
        return updateMessage(recipientId, messageId, messageDetails);
    }

    @DeleteMapping("/{messageId}/")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long recipientId,
            @PathVariable Long messageId) {

        ServiceResult<MessageDto> message = messageService.getMessageById(messageId);

        if (!message.getDataOrNull().getRecipientId().equals(recipientId)) {
            throw new ResourceNotFoundException(
                    "Message not found with id " + messageId + " for recipient id " + recipientId);
        }

        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
