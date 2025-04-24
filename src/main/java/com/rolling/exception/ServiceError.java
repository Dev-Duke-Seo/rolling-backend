package com.rolling.exception;

import org.springframework.http.HttpStatus;

public class ServiceError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ServiceErrorCode errorCode;
    private final Object[] args;

    public ServiceError(ServiceErrorCode errorCode, Object... args) {
        super(errorCode.formatMessage(args));
        this.errorCode = errorCode;
        this.args = args;
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }

    public ServiceErrorCode getServiceErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    // 자주 발생하는 에러에 대한 정적 팩토리 메소드들

    public static ServiceError notFound(String resourceType, Long id) {
        return new ServiceError(ServiceErrorCode.RESOURCE_NOT_FOUND,
                resourceType + " (id: " + id + ")");
    }

    public static ServiceError recipientNotFound(Long id) {
        return new ServiceError(ServiceErrorCode.RECIPIENT_NOT_FOUND, id);
    }

    public static ServiceError messageNotFound(Long id) {
        return new ServiceError(ServiceErrorCode.MESSAGE_NOT_FOUND, id);
    }

    public static ServiceError reactionNotFound(Long id) {
        return new ServiceError(ServiceErrorCode.REACTION_NOT_FOUND, id);
    }

    public static ServiceError badRequest(String details) {
        return new ServiceError(ServiceErrorCode.INVALID_REQUEST, details);
    }

    public static ServiceError forbidden(String details) {
        return new ServiceError(ServiceErrorCode.FORBIDDEN_ACCESS, details);
    }

    public static ServiceError conflict(String details) {
        return new ServiceError(ServiceErrorCode.DUPLICATE_ENTITY, details);
    }

    public static ServiceError internalError(String details) {
        return new ServiceError(ServiceErrorCode.INTERNAL_ERROR, details);
    }
}
