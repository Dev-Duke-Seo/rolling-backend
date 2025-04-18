package com.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class UserRegistrationDto {
    private String username;
    private String password;
    private String email;
    private String nickname;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class UserLoginDto {
    private String username;
    private String password;
}