package com.blob.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlobApiResponse<T> {

    private int status;
    private String message;
    private T data;
    private String errorCode;
    private String timestamp;

    public static <T> BlobApiResponse<T> success(T data) {
        return BlobApiResponse.<T>builder().status(200).message("Success").data(data).build();
    }

    public static <T> BlobApiResponse<T> success(String message, T data) {
        return BlobApiResponse.<T>builder().status(200).message(message).data(data).build();
    }

    public static <T> BlobApiResponse<T> error(int status, String message) {
        return BlobApiResponse.<T>builder().status(status).message(message)
                .timestamp(java.time.Instant.now().toString()).build();
    }

    public static <T> BlobApiResponse<T> error(int status, String message, String errorCode) {
        return BlobApiResponse.<T>builder().status(status).message(message).errorCode(errorCode)
                .timestamp(java.time.Instant.now().toString()).build();
    }
}
