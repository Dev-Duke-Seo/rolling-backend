package com.common.exception;

import org.springframework.http.HttpStatus;

public enum ServiceErrorCode {
    // 리소스를 찾을 수 없음
    RECIPIENT_NOT_FOUND("recipient.not_found", "수신자(id: %s)를 찾을 수 없습니다",
            HttpStatus.NOT_FOUND), MESSAGE_NOT_FOUND("message.not_found", "메시지(id: %s)를 찾을 수 없습니다",
                    HttpStatus.NOT_FOUND), REACTION_NOT_FOUND("reaction.not_found",
                            "리액션(id: %s)를 찾을 수 없습니다",
                            HttpStatus.NOT_FOUND), RESOURCE_NOT_FOUND("resource.not_found",
                                    "리소스(%s)를 찾을 수 없습니다", HttpStatus.NOT_FOUND),

    // 유효성 검사 실패
    INVALID_REQUEST("invalid.request", "잘못된 요청입니다: %s", HttpStatus.BAD_REQUEST), INVALID_RECIPIENT(
            "invalid.recipient", "유효하지 않은 수신자 정보입니다: %s", HttpStatus.BAD_REQUEST), INVALID_MESSAGE(
                    "invalid.message", "유효하지 않은 메시지 정보입니다: %s", HttpStatus.BAD_REQUEST),

    // 권한 관련
    FORBIDDEN_ACCESS("forbidden.access", "접근 권한이 없습니다: %s", HttpStatus.FORBIDDEN),

    // 충돌
    DUPLICATE_ENTITY("duplicate.entity", "이미 존재하는 엔티티입니다: %s", HttpStatus.CONFLICT),

    // 서버 오류
    INTERNAL_ERROR("internal.error", "내부 서버 오류가 발생했습니다: %s", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String messageTemplate;
    private final HttpStatus status;

    ServiceErrorCode(String code, String messageTemplate, HttpStatus status) {
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String formatMessage(Object... args) {
        return String.format(messageTemplate, args);
    }
}
