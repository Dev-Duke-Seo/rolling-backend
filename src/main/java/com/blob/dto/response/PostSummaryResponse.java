
package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.blob.entity.Post;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSummaryResponse {

    private Long postId;
    private String authorBlobId;
    private String authorNickname;
    private String title;
    private String category;
    private String subcategory;
    private String thumbnailUrl;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private LocalDateTime createdAt;

    public static PostSummaryResponse from(Post post) {
        return PostSummaryResponse.builder().postId(post.getPostId())
                .authorBlobId(post.getUser().getBlobId())
                .authorNickname(post.getUser().getNickname()).title(post.getTitle())
                .category(post.getCategory()).subcategory(post.getSubcategory())
                .thumbnailUrl(
                        post.getImages().isEmpty() ? null : post.getImages().get(0).getImageUrl())
                .likeCount(post.getLikeCount()).commentCount(post.getCommentCount())
                .viewCount(post.getViewCount()).createdAt(post.getCreatedAt()).build();
    }
}
