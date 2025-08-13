package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.blob.entity.Notification;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long notificationId;
    private Long postId;
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public static NotificationResponse from(Notification notification) {
        return NotificationResponse.builder().notificationId(notification.getNotificationId())
                .postId(notification.getRelatedId()).message(notification.getContent())
                .isRead(notification.getIsRead()).createdAt(notification.getCreatedAt()).build();
    }
}
