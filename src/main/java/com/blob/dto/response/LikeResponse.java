package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {

    private boolean liked;
    private int likeCount;

    public static LikeResponse of(boolean liked, int likeCount) {
        return LikeResponse.builder().liked(liked).likeCount(likeCount).build();
    }
}
