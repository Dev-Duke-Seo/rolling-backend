package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {

    private boolean bookmarked;

    public static BookmarkResponse of(boolean bookmarked) {
        return BookmarkResponse.builder().bookmarked(bookmarked).build();
    }
}
