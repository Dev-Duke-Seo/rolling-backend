package com.blob.dto.response;

import com.blob.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerDataResponse {

    private Long postId;
    private String title;
    private String category;
    private Double latitude;
    private Double longitude;
    private String address;
    private String thumbnailUrl;

    public static MarkerDataResponse from(Post post) {
        return MarkerDataResponse.builder().postId(post.getPostId()).title(post.getTitle())
                .category(post.getCategory()).latitude(post.getLatitude())
                .longitude(post.getLongitude()).address(post.getAddress())
                .thumbnailUrl(
                        post.getImages().isEmpty() ? null : post.getImages().get(0).getImageUrl())
                .build();
    }
}
