package com.blob.service;

import org.springframework.web.multipart.MultipartFile;
import com.blob.dto.request.CreateBlobUserRequest;
import com.blob.dto.request.UpdateProfileRequest;
import com.blob.dto.response.BlobApiResponse;
import com.blob.dto.response.BlobPagedResponse;
import com.blob.dto.response.BlobUserResponse;
import com.blob.dto.response.PostSummaryResponse;

public interface BlobUserService {

    BlobApiResponse<BlobUserResponse> createUser(CreateBlobUserRequest request);

    BlobApiResponse<BlobUserResponse> getUserDetail(String blobId);

    BlobApiResponse<Void> deleteUser(Long userId);

    BlobApiResponse<Boolean> checkBlobId(String blobId);

    BlobApiResponse<BlobUserResponse> updateProfile(Long userId, MultipartFile profileImage,
            UpdateProfileRequest request);

    BlobApiResponse<Void> deleteProfileImage(Long userId);

    BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> getUserPosts(String blobId, int page,
            int size);

    BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> getUserBookmarks(String blobId,
            int page, int size);
}
