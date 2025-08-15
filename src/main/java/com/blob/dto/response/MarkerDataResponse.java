package com.blob.dto.response;

import java.time.format.DateTimeFormatter;
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
    private String subCategory;
    private Double lat;
    private Double lng;
    private String address;
    private String thumbnailUrl;
    private String createdDate;

    public static MarkerDataResponse from(Post post) {
        return MarkerDataResponse.builder().postId(post.getPostId()).title(post.getTitle())
                .category(post.getCategory()).subCategory(post.getSubcategory())
                .lat(post.getLatitude()).lng(post.getLongitude()).address(post.getAddress())
                .thumbnailUrl(
                        post.getImages().isEmpty() ? null : post.getImages().get(0).getImageUrl())
                .createdDate(post.getCreatedDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
