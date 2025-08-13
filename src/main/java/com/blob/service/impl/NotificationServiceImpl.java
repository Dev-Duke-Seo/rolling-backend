package com.blob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blob.dto.response.BlobApiResponse;
import com.blob.dto.response.BlobPagedResponse;
import com.blob.dto.response.NotificationResponse;
import com.blob.entity.BlobUser;
import com.blob.entity.Notification;
import com.blob.repository.BlobUserRepository;
import com.blob.repository.NotificationRepository;
import com.blob.service.NotificationService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final BlobUserRepository userRepository;

    @Override
    public BlobApiResponse<BlobPagedResponse<NotificationResponse>> getNotifications(Long userId, int page, int size) {
        try {
            // 사용자 존재 확인
            BlobUser user = userRepository.findById(userId).orElse(null);
            if (user == null || user.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), 
                    "사용자를 찾을 수 없습니다.", "USER_NOT_FOUND");
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<Notification> notifications = notificationRepository.findByUserId(userId, pageable);

            BlobPagedResponse<NotificationResponse> response = BlobPagedResponse.<NotificationResponse>builder()
                    .content(notifications.getContent().stream()
                            .map(NotificationResponse::from)
                            .toList())
                    .totalElements(notifications.getTotalElements())
                    .totalPages(notifications.getTotalPages())
                    .size(notifications.getSize())
                    .number(notifications.getNumber())
                    .first(notifications.isFirst())
                    .last(notifications.isLast())
                    .empty(notifications.isEmpty())
                    .build();

            return BlobApiResponse.success(response);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                "알림 조회 중 오류가 발생했습니다.", "NOTIFICATION_FETCH_ERROR");
        }
    }

    @Override
    @Transactional
    public BlobApiResponse<Void> markAsRead(Long userId, Long notificationId) {
        try {
            Notification notification = notificationRepository.findById(notificationId).orElse(null);
            if (notification == null) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), 
                    "알림을 찾을 수 없습니다.", "NOTIFICATION_NOT_FOUND");
            }

            // 알림 소유자 확인
            if (!notification.getUser().getUserId().equals(userId)) {
                return BlobApiResponse.error(HttpStatus.FORBIDDEN.value(), 
                    "알림에 접근할 권한이 없습니다.", "FORBIDDEN");
            }

            // 읽음 상태로 변경
            notification.setIsRead(true);
            notificationRepository.save(notification);

            return BlobApiResponse.success(null);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                "알림 읽음 처리 중 오류가 발생했습니다.", "NOTIFICATION_READ_ERROR");
        }
    }

    @Override
    @Transactional
    public BlobApiResponse<Void> markAllAsRead(Long userId) {
        try {
            // 사용자 존재 확인
            BlobUser user = userRepository.findById(userId).orElse(null);
            if (user == null || user.getIsDeleted()) {
                return BlobApiResponse.error(HttpStatus.NOT_FOUND.value(), 
                    "사용자를 찾을 수 없습니다.", "USER_NOT_FOUND");
            }

            // 해당 사용자의 모든 안읽은 알림을 읽음 상태로 변경
            notificationRepository.markAllAsReadByUserId(userId);

            return BlobApiResponse.success(null);

        } catch (Exception e) {
            return BlobApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                "전체 알림 읽음 처리 중 오류가 발생했습니다.", "NOTIFICATION_READ_ALL_ERROR");
        }
    }
}