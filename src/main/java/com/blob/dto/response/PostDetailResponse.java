package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import com.blob.entity.Post;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponse {

    private Long postId;
    private String title;
    private String content;
    private String category;
    private String subcategory;
    private AuthorDto author;
    private String country;
    private String city;
    private Double cityLat;
    private Double cityLng;
    private Double lat;
    private Double lng;
    private String address;
    private Double actualLat;
    private Double actualLng;
    private Double distFromActual;
    private Integer views;
    private String createdDate;
    private String expiresAt;
    private List<String> imageUrl;
    private Boolean liked;
    private Boolean bookmarked;
    private Integer likeCount;
    private Integer commentCount;
    private Boolean canDelete;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorDto {
        private Long userId;
        private String blobId;
        private String nickname;
        private String profileUrl;
        private Integer likedCount;
    }

    public static PostDetailResponse from(Post post) {
        AuthorDto author = AuthorDto.builder().userId(post.getUser().getUserId())
                .blobId(post.getUser().getBlobId()).nickname(post.getUser().getNickname())
                .profileUrl(post.getUser().getProfileImageUrl())
                .likedCount(post.getUser().getLikedCount()).build();

        return PostDetailResponse.builder().postId(post.getPostId()).title(post.getTitle())
                .content(post.getContent()).category(post.getCategory())
                .subcategory(post.getSubcategory()).author(author).country(post.getCountry())
                .city(post.getCity()).cityLat(post.getCityLat()).cityLng(post.getCityLng())
                .lat(post.getLatitude()).lng(post.getLongitude()).address(post.getAddress())
                .actualLat(post.getActualLat()).actualLng(post.getActualLng())
                .distFromActual(post.getDistFromActual()).views(post.getViewCount())
                .createdDate(post.getCreatedDate().toString())
                .expiresAt(post.getExpiresAt() != null ? post.getExpiresAt().toString() : null)
                .imageUrl(post.getImages().stream().map(image -> image.getImageUrl())
                        .collect(Collectors.toList()))
                .likeCount(post.getLikeCount()).commentCount(post.getCommentCount()).liked(false) // TODO:
                                                                                                  // 현재
                                                                                                  // 사용자
                                                                                                  // 좋아요
                                                                                                  // 여부
                                                                                                  // 확인
                                                                                                  // 로직
                                                                                                  // 필요
                .bookmarked(false) // TODO: 현재 사용자 북마크 여부 확인 로직 필요
                .canDelete(false) // TODO: 삭제 권한 확인 로직 필요
                .build();
    }
}
