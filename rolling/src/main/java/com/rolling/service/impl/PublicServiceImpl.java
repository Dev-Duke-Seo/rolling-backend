package com.rolling.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rolling.model.ServiceResult;
import com.rolling.service.PublicService;

import java.util.Arrays;

@Service
public class PublicServiceImpl implements PublicService {
    @Override
    public ServiceResult getBackgroundImage() {
        Map<String, Object> data = new HashMap<>();
        List<String> imageUrls = Arrays.asList("https://picsum.photos/id/683/3840/2160",
                "https://picsum.photos/id/24/3840/2160", "https://picsum.photos/id/599/3840/2160",
                "https://picsum.photos/id/1058/3840/2160");

        data.put("imageUrls", imageUrls);

        return ServiceResult.success("Background image fetched successfully", data);
    }

    @Override
    public ServiceResult getProfileImage() {
        Map<String, Object> data = new HashMap<>();
        List<String> imageUrls = Arrays.asList(
                "https://learn-codeit-kr-static.s3.ap-northeast-2.amazonaws.com/sprint-proj-image/default_avatar.png",
                "https://picsum.photos/id/522/100/100", "https://picsum.photos/id/547/100/100",
                "https://picsum.photos/id/268/100/100", "https://picsum.photos/id/1082/100/100",
                "https://picsum.photos/id/571/100/100", "https://picsum.photos/id/494/100/100",
                "https://picsum.photos/id/859/100/100", "https://picsum.photos/id/437/100/100",
                "https://picsum.photos/id/1064/100/100");

        data.put("imageUrls", imageUrls);
        return ServiceResult.success("Profile image fetched successfully", data);
    }
}
