package com.rolling.model;

public class ServiceResult {
    private boolean isSuccess;
    private String message;
    private Object data;

    public ServiceResult(boolean isSuccess, String message, Object data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static ServiceResult success(String message, Object data) {
        return new ServiceResult(true, message, data);
    }

    public static ServiceResult error(String message) {
        return new ServiceResult(false, message, null);
    }
}
