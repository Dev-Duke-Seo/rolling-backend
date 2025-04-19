package com.rolling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rolling.dto.PageResponseDto;
import com.rolling.dto.RecipientDto;
import com.rolling.model.ServiceResult;
import com.rolling.model.entity.Recipient;
import com.rolling.service.RecipientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recipients")
@RequiredArgsConstructor
public class RecipientController {

    private final RecipientService recipientService;

    @PostMapping("/")
    public ResponseEntity<RecipientDto> createRecipient(@RequestBody Recipient recipient) {
        ServiceResult<RecipientDto> result = recipientService.createRecipient(recipient);

        if (!result.isSuccess()) {
            return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }

    @GetMapping("/{id}/")
    public ResponseEntity<RecipientDto> getRecipientById(@PathVariable Long id) {
        ServiceResult<RecipientDto> result = recipientService.getRecipientById(id);

        if (!result.isSuccess()) {
            return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
    }

    @GetMapping("/")
    public ResponseEntity<PageResponseDto<RecipientDto>> getAllRecipients(
            @RequestParam(defaultValue = "8") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        ServiceResult<PageResponseDto<RecipientDto>> result =
                recipientService.getAllRecipients(limit, offset);

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
}
