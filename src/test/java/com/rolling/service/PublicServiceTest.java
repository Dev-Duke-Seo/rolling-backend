package com.rolling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rolling.model.ServiceResult;
import com.rolling.service.impl.PublicServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PublicServiceTest {

    @InjectMocks
    private PublicServiceImpl publicService;

    @Test
    @DisplayName("배경 이미지 URL 목록 가져오기 테스트")
    void getBackgroundImage_ShouldReturnImageUrls() {
        // when
        ServiceResult<Object> result = publicService.getBackgroundImage();

        // then
        assertTrue(result.isSuccess());
        assertEquals("Background image fetched successfully", result.getMessage());

        Map<String, Object> data = (Map<String, Object>) result.getDataOrNull();
        List<String> imageUrls = (List<String>) data.get("imageUrls");

        assertEquals(4, imageUrls.size());
        assertEquals("https://picsum.photos/id/683/3840/2160", imageUrls.get(0));
        assertEquals("https://picsum.photos/id/24/3840/2160", imageUrls.get(1));
        assertEquals("https://picsum.photos/id/599/3840/2160", imageUrls.get(2));
        assertEquals("https://picsum.photos/id/1058/3840/2160", imageUrls.get(3));
    }

    @Test
    @DisplayName("프로필 이미지 URL 목록 가져오기 테스트")
    void getProfileImage_ShouldReturnImageUrls() {
        // when
        ServiceResult<Object> result = publicService.getProfileImage();

        // then
        assertTrue(result.isSuccess());
        assertEquals("Profile image fetched successfully", result.getMessage());

        Map<String, Object> data = (Map<String, Object>) result.getDataOrNull();
        List<String> imageUrls = (List<String>) data.get("imageUrls");

        assertEquals(10, imageUrls.size());
        assertEquals(
                "https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png",
                imageUrls.get(0));
        assertEquals("https://picsum.photos/id/522/100/100", imageUrls.get(1));
        assertEquals("https://picsum.photos/id/547/100/100", imageUrls.get(2));
    }
}
