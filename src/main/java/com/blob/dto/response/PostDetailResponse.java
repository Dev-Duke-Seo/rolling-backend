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
public class PostDetailResponse {

    private Long postId;
    private BlobUserResponse author;
    private String title;
    private String content;
    private String category;
    private String subcategory;
    private Double latitude;
    private Double longitude;
    private String address;
    private List<String> imageUrls;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostDetailResponse from(Post post) {
        return PostDetailResponse.builder().postId(post.getPostId())
                .author(BlobUserResponse.from(post.getUser())).title(post.getTitle())
                .content(post.getContent()).category(post.getCategory())
                .subcategory(post.getSubcategory()).latitude(post.getLatitude())
                .longitude(post.getLongitude()).address(post.getAddress())
                .imageUrls(post.getImages().stream().map(image -> image.getImageUrl())
                        .collect(Collectors.toList()))
                .likeCount(post.getLikeCount()).commentCount(post.getCommentCount())
                .viewCount(post.getViewCount()).createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt()).build();
    }
}
