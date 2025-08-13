package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.blob.entity.BlobUser;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlobUserResponse {

    private Long userId;
    private String email;
    private String blobId;
    private String nickname;
    private String bio;
    private String profileUrl;
    private String state;
    private Integer postCount;
    private Integer likedCount;
    private Integer commentCount;
    private Boolean isPublic;
    private CoordinateDto coordinate;
    private String oauthType;
    private String role;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CoordinateDto {
        private Double lat;
        private Double lng;
    }

    public static BlobUserResponse from(BlobUser user) {
        CoordinateDto coordinate = null;
        if (user.getCoordinateLat() != null && user.getCoordinateLng() != null) {
            coordinate = CoordinateDto.builder()
                    .lat(user.getCoordinateLat())
                    .lng(user.getCoordinateLng())
                    .build();
        }

        return BlobUserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .blobId(user.getBlobId())
                .nickname(user.getNickname())
                .bio(user.getBio())
                .profileUrl(user.getProfileImageUrl())
                .state(user.getState())
                .postCount(user.getPostCount())
                .likedCount(user.getLikedCount())
                .commentCount(user.getCommentCount())
                .isPublic(user.getIsPublic())
                .coordinate(coordinate)
                .oauthType(user.getOauthType())
                .role(user.getRole())
                .build();
    }
}
