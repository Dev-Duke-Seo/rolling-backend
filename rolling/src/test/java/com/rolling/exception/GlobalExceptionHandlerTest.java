package com.rolling.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webRequest.getDescription(false)).thenReturn("test-description");
    }

    @Test
    void resourceNotFoundException_ShouldReturnNotFoundStatus() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");

        ResponseEntity<?> responseEntity = globalExceptionHandler.resourceNotFoundException(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals("Resource not found", errorDetails.getMessage());
        assertEquals("test-description", errorDetails.getDetails());
        assertNotNull(errorDetails.getTimestamp());
    }

    @Test
    void noHandlerFoundException_ShouldReturnNotFoundStatus() {
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/not-found", null);

        ResponseEntity<?> responseEntity = globalExceptionHandler.noHandlerFoundException(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals("요청한 리소스를 찾을 수 없습니다: /not-found", errorDetails.getMessage());
        assertEquals("test-description", errorDetails.getDetails());
        assertNotNull(errorDetails.getTimestamp());
    }

    @Test
    void globalExceptionHandler_ShouldReturnInternalServerErrorStatus() {
        Exception ex = new RuntimeException("Internal server error");

        ResponseEntity<?> responseEntity = globalExceptionHandler.globalExceptionHandler(ex, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals("Internal server error", errorDetails.getMessage());
        assertEquals("test-description", errorDetails.getDetails());
        assertNotNull(errorDetails.getTimestamp());
    }
}