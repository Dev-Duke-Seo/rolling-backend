package com.rolling.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.rolling.model.ServiceResult;
import com.rolling.service.PublicService;

@WebMvcTest(PublicController.class)
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicService publicService;

    private Map<String, Object> backgroundImageData;
    private Map<String, Object> profileImageData;

    @BeforeEach
    void setUp() {
        backgroundImageData = new HashMap<>();
        List<String> backgroundImages = Arrays.asList("https://picsum.photos/id/683/3840/2160",
                "https://picsum.photos/id/24/3840/2160", "https://picsum.photos/id/599/3840/2160",
                "https://picsum.photos/id/1058/3840/2160");
        backgroundImageData.put("imageUrls", backgroundImages);

        profileImageData = new HashMap<>();
        List<String> profileImages = Arrays.asList(
                "https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png",
                "https://picsum.photos/id/522/100/100", "https://picsum.photos/id/547/100/100",
                "https://picsum.photos/id/268/100/100");
        profileImageData.put("imageUrls", profileImages);
    }

    @Test
    @DisplayName("배경 이미지 URL 목록 가져오기 테스트")
    void getBackgroundImage() throws Exception {
        // given
        when(publicService.getBackgroundImage()).thenReturn(ServiceResult
                .success("Background image fetched successfully", backgroundImageData));

        // when & then
        mockMvc.perform(get("/public/background-images/")).andExpect(status().isOk())
                .andExpect(jsonPath("$.imageUrls").isArray())
                .andExpect(jsonPath("$.imageUrls.length()").value(4))
                .andExpect(
                        jsonPath("$.imageUrls[0]").value("https://picsum.photos/id/683/3840/2160"))
                .andExpect(
                        jsonPath("$.imageUrls[1]").value("https://picsum.photos/id/24/3840/2160"))
                .andExpect(
                        jsonPath("$.imageUrls[2]").value("https://picsum.photos/id/599/3840/2160"))
                .andExpect(jsonPath("$.imageUrls[3]")
                        .value("https://picsum.photos/id/1058/3840/2160"));
    }

    @Test
    @DisplayName("프로필 이미지 URL 목록 가져오기 테스트")
    void getProfileImage() throws Exception {
        // given
        when(publicService.getProfileImage()).thenReturn(
                ServiceResult.success("Profile image fetched successfully", profileImageData));

        // when & then
        mockMvc.perform(get("/public/profile-images/")).andExpect(status().isOk())
                .andExpect(jsonPath("$.imageUrls").isArray())
                .andExpect(jsonPath("$.imageUrls.length()").value(4))
                .andExpect(jsonPath("$.imageUrls[0]").value(
                        "https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png"))
                .andExpect(jsonPath("$.imageUrls[1]").value("https://picsum.photos/id/522/100/100"))
                .andExpect(jsonPath("$.imageUrls[2]").value("https://picsum.photos/id/547/100/100"))
                .andExpect(
                        jsonPath("$.imageUrls[3]").value("https://picsum.photos/id/268/100/100"));
    }
}
