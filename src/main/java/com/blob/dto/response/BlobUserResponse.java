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
    private String blobId;
    private String nickname;
    private String email;
    private String profileImageUrl;
    private LocalDateTime createdAt;

    public static BlobUserResponse from(BlobUser user) {
        return BlobUserResponse.builder().userId(user.getUserId()).blobId(user.getBlobId())
                .nickname(user.getNickname()).email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl()).createdAt(user.getCreatedAt()).build();
    }
}
