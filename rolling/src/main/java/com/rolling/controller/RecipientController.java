package com.rolling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rolling.model.ServiceResult;
import com.rolling.model.dto.MessageDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.ReactionCreateDto;
import com.rolling.model.dto.ReactionDto;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.dto.Recipient.RecipientDto;
import com.rolling.model.entity.Message;
import com.rolling.service.RecipientService;
import com.rolling.service.MessageService;
import com.rolling.service.ReactionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recipients")
@RequiredArgsConstructor
public class RecipientController {

    private final RecipientService recipientService;
    private final MessageService messageService;
    private final ReactionService reactionService;

    @GetMapping("/")
    public ResponseEntity<PageResponseDto<RecipientDto>> getAllRecipients(
            @RequestParam(name = "limit", defaultValue = "8") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset) {
        System.out.println("limit: " + limit);
        ServiceResult<PageResponseDto<RecipientDto>> result =
                recipientService.getAllRecipients(limit, offset);

        if (!result.isSuccess()) {
            return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }

    @PostMapping("/")
    public ResponseEntity<RecipientDto> createRecipient(@RequestBody RecipientCreateDto recipient) {
        System.out.println("recipient: " + recipient);
        ServiceResult<RecipientDto> result = recipientService.createRecipient(recipient);


        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }


    @GetMapping("/{id}/")
    public ResponseEntity<RecipientDto> getRecipientById(@PathVariable("id") Long id) {
        ServiceResult<RecipientDto> result = recipientService.getRecipientById(id);

        if (!result.isSuccess()) {
            return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }


    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deleteRecipient(@PathVariable Long id) {
        recipientService.deleteRecipient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/messages/")
    public ResponseEntity<MessageDto> createMessage(@PathVariable("id") Long id,
            @RequestBody Message message) {

        ServiceResult<MessageDto> result = messageService.createMessage(message, id);

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }

    @GetMapping("/{id}/messages/")
    public ResponseEntity<PageResponseDto<MessageDto>> getMessages(@PathVariable("id") Long id,
            @RequestParam(name = "limit", defaultValue = "8") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset) {
        ServiceResult<PageResponseDto<MessageDto>> result =
                messageService.getMessagesByRecipientId(id, limit, offset);

        if (!result.isSuccess()) {
            return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }

    @GetMapping("/{id}/reactions/")
    public ResponseEntity<PageResponseDto<ReactionDto>> getReactionsByRecipientId(
            @PathVariable("id") Long id,
            @RequestParam(name = "limit", defaultValue = "8") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset) {
        ServiceResult<PageResponseDto<ReactionDto>> result =
                reactionService.getReactionsByRecipientId(id, limit, offset);

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }

    @PostMapping("/{id}/reactions/")
    public ResponseEntity<ReactionDto> createReaction(@PathVariable("id") Long id,
            @RequestBody ReactionCreateDto reactionCreateDto) {
        ServiceResult<ReactionDto> result = reactionService.addReaction(id, reactionCreateDto);

        if (!result.isSuccess()) {
            return ResponseEntity.status(result.getStatus()).body(null);
        }

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }


}
