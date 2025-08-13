package com.blob.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.blob.dto.response.*;
import com.blob.service.NotificationService;

@RestController
@RequestMapping("/blob/notification")
@RequiredArgsConstructor
@Tag(name = "BLOB Notification", description = "BLOB 알림 관련 API")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "알림 목록 조회", description = "사용자의 알림 목록을 조회합니다")
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<BlobPagedResponse<NotificationResponse>>> getNotifications(
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<BlobPagedResponse<NotificationResponse>> response = 
                notificationService.getNotifications(userId, page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "알림 읽음 처리", description = "특정 알림을 읽음 상태로 변경합니다")
    @PostMapping("/read/{notificationId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> markAsRead(
            @PathVariable Long notificationId,
            Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<Void> response = notificationService.markAsRead(userId, notificationId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "모든 알림 읽음 처리", description = "사용자의 모든 알림을 읽음 상태로 변경합니다")
    @PostMapping("/readAll")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BlobApiResponse<Void>> markAllAsRead(Authentication authentication) {
        
        Long userId = Long.parseLong(authentication.getName());
        BlobApiResponse<Void> response = notificationService.markAllAsRead(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}