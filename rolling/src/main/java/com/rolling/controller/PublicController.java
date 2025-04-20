package com.rolling.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolling.model.ServiceResult;
import com.rolling.service.PublicService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final PublicService publicService;

    @GetMapping("/background-images/")
    public ResponseEntity<Object> getBackgroundImage() {

        ServiceResult serviceResult = publicService.getBackgroundImage();

        if (!serviceResult.isSuccess()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(serviceResult.getMessage());
        }

        return ResponseEntity.ok(serviceResult.getDataOrNull());
    }

    @GetMapping("/profile-images/")
    public ResponseEntity<Object> getProfileImage() {
        ServiceResult serviceResult = publicService.getProfileImage();

        return ResponseEntity.ok(serviceResult.getData());
    }
}
