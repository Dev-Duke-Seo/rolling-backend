package com.blob.service;

import org.springframework.web.multipart.MultipartFile;
import com.blob.dto.request.PostCreateRequest;
import com.blob.dto.request.ReportRequest;
import com.blob.dto.response.*;
import java.util.List;

public interface PostService {

        BlobApiResponse<PostResponse> createPost(Long userId, List<MultipartFile> images,
                        PostCreateRequest request);

        BlobApiResponse<PostDetailResponse> getPost(Long postId);

        BlobApiResponse<Void> deletePost(Long userId, Long postId);

        BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> getFeed(int page, int size,
                        Double cityLat, Double cityLng, String sortBy, String categories,
                        String startDate, String endDate, boolean hasImage, boolean hasLocation,
                        int minLikes, String keyword);

        BlobApiResponse<List<MarkerDataResponse>> getMarkers(String categories, double minLat,
                        double maxLat, double minLng, double maxLng);

        BlobApiResponse<BlobPagedResponse<MarkerDataResponse>> getMapSidebarPosts(String categories, 
                        double minLat, double maxLat, double minLng, double maxLng, 
                        int page, int size, String sortBy);

        BlobApiResponse<LikeResponse> togglePostLike(Long userId, Long postId);

        BlobApiResponse<BookmarkResponse> toggleBookmark(Long userId, Long postId);

        BlobApiResponse<Void> reportPost(Long userId, Long postId, ReportRequest request);
}
