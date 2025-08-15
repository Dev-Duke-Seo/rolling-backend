package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.blob.entity.Post;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long postId;
    private String title;
    private String content;
    private String category;
    private String subcategory;
    private Double latitude;
    private Double longitude;
    private String address;
    private List<String> imageUrls;
    private LocalDateTime createdDate;

    public static PostResponse from(Post post) {
        return PostResponse.builder().postId(post.getPostId()).title(post.getTitle())
                .content(post.getContent()).category(post.getCategory())
                .subcategory(post.getSubcategory()).latitude(post.getLatitude())
                .longitude(post.getLongitude()).address(post.getAddress())
                .imageUrls(post.getImages().stream().map(image -> image.getImageUrl())
                        .collect(Collectors.toList()))
                .createdDate(post.getCreatedDate()).build();
    }
}
