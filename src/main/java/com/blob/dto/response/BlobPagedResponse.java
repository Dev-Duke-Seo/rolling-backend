package com.blob.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlobPagedResponse<T> {

    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;
    private boolean first;
    private boolean last;
    private boolean empty;

    public static <T> BlobPagedResponse<T> from(Page<T> page) {
        return BlobPagedResponse.<T>builder().content(page.getContent())
                .totalElements(page.getTotalElements()).totalPages(page.getTotalPages())
                .size(page.getSize()).number(page.getNumber()).first(page.isFirst())
                .last(page.isLast()).empty(page.isEmpty()).build();
    }
}
