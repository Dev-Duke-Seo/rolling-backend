package com.common.dto;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceResult<T> {
    private final boolean isSuccess;
    private HttpStatus status;
    private String message;
    private T data;

    @Builder
    private ServiceResult(boolean isSuccess, HttpStatus status, String message, T data) {
        this.isSuccess = isSuccess;
        this.status = status != null ? status
                : isSuccess ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message != null ? message : isSuccess ? "success" : "error";
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public HttpStatus getStatus() {
        return status;
    }

    /**
     * 데이터를 Optional로 래핑하여 반환
     * 
     * @return 데이터를 포함한 Optional 객체 (데이터가 없는 경우 Optional.empty())
     */
    public Optional<T> getData() {
        return Optional.ofNullable(data);
    }

    /**
     * 데이터를 직접 반환 (null일 수 있음)
     * 
     * @return 데이터 (null일 수 있음)
     */
    public T getDataOrNull() {
        return data;
    }

    /**
     * 데이터가 없는 성공 결과 생성
     * 
     * @param message 성공 메시지
     * @return 데이터가 없는 성공 결과 객체
     */
    public static <T> ServiceResult<T> success(String message) {
        return ServiceResult.<T>builder().isSuccess(true).message(message).build();
    }

    public static <T> ServiceResult<T> success(T data) {
        return ServiceResult.<T>builder().isSuccess(true).data(data).build();
    }

    /**
     * 데이터가 있는 성공 결과 생성
     * 
     * @param message 성공 메시지
     * @param data 결과 데이터 (non-null)
     * @return 성공 결과 객체
     */
    public static <T> ServiceResult<T> success(String message, T data) {
        return ServiceResult.<T>builder().isSuccess(true).message(message).data(data).build();
    }


    /**
     * 커스텀 상태코드로 성공 결과 생성
     * 
     * @param status HTTP 상태코드
     * @param message 성공 메시지
     * @param data 결과 데이터
     * @return 성공 결과 객체
     */
    public static <T> ServiceResult<T> success(String message, T data, HttpStatus status) {
        return ServiceResult.<T>builder().isSuccess(true).status(status).message(message).data(data)
                .build();
    }



    /**
     * 기본 오류 결과 생성 (INTERNAL_SERVER_ERROR)
     * 
     * @param message 오류 메시지
     * @return 오류 결과 객체
     */
    public static <T> ServiceResult<T> error(String message) {
        return ServiceResult.<T>builder().isSuccess(false).build();
    }

    /**
     * 커스텀 상태코드로 오류 결과 생성
     * 
     * @param status HTTP 상태코드
     * @param message 오류 메시지
     * @return 오류 결과 객체
     */
    public static <T> ServiceResult<T> error(String message, HttpStatus status) {
        return ServiceResult.<T>builder().isSuccess(false).message(message).status(status).build();
    }

}
