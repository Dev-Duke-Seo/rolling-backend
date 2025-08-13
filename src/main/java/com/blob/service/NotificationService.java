package com.blob.service;

import com.blob.dto.response.BlobApiResponse;
import com.blob.dto.response.BlobPagedResponse;
import com.blob.dto.response.NotificationResponse;

public interface NotificationService {
    
    BlobApiResponse<BlobPagedResponse<NotificationResponse>> getNotifications(Long userId, int page, int size);
    
    BlobApiResponse<Void> markAsRead(Long userId, Long notificationId);
    
    BlobApiResponse<Void> markAllAsRead(Long userId);
}