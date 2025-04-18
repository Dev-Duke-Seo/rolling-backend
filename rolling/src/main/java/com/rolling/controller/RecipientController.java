package com.rolling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rolling.entity.Recipient;
import com.rolling.dto.PageResponseDto;
import com.rolling.dto.RecipientDto;
import com.rolling.service.RecipientService;

@RestController
@RequestMapping("/recipients")
public class RecipientController {

    private final RecipientService recipientService;

    @Autowired
    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @PostMapping("/")
    public ResponseEntity<RecipientDto> createRecipient(@RequestBody Recipient recipient) {
        RecipientDto createdRecipient = recipientService.createRecipient(recipient);
        return new ResponseEntity<>(createdRecipient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<RecipientDto> getRecipientById(@PathVariable Long id) {
        RecipientDto recipient = recipientService.getRecipientById(id);
        return ResponseEntity.ok(recipient);
    }

    @GetMapping("/")
    public ResponseEntity<PageResponseDto<RecipientDto>> getAllRecipients(
            @RequestParam(defaultValue = "8") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        PageResponseDto<RecipientDto> recipients = recipientService.getAllRecipients(limit, offset);
        return ResponseEntity.ok(recipients);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deleteRecipient(@PathVariable Long id) {
        recipientService.deleteRecipient(id);
        return ResponseEntity.noContent().build();
    }
}
