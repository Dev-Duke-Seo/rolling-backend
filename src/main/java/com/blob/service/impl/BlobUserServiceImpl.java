package com.blob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.blob.dto.request.CreateBlobUserRequest;
import com.blob.dto.request.UpdateProfileRequest;
import com.blob.dto.response.*;
import com.blob.entity.BlobUser;
import com.blob.entity.Bookmark;
import com.blob.entity.Post;
import com.blob.repository.BlobUserRepository;
import com.blob.repository.BookmarkRepository;
import com.blob.repository.PostRepository;
import com.blob.service.BlobUserService;

@Service
@RequiredArgsConstructor
@Transactional
public class BlobUserServiceImpl implements BlobUserService {

    private final BlobUserRepository blobUserRepository;
    private final PostRepository postRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public BlobApiResponse<BlobUserResponse> createUser(CreateBlobUserRequest request) {
        if (blobUserRepository.existsByBlobId(request.getBlobId())) {
            return BlobApiResponse.error(400, "이미 존재하는 Blob ID입니다", "USER_002");
        }

        BlobUser user = BlobUser.builder().blobId(request.getBlobId())
                .nickname(request.getNickname()).email(request.getEmail()).build();

        BlobUser savedUser = blobUserRepository.save(user);
        return BlobApiResponse.success(BlobUserResponse.from(savedUser));
    }

    @Override
    @Transactional(readOnly = true)
    public BlobApiResponse<BlobUserResponse> getUserDetail(String blobId) {
        BlobUser user = blobUserRepository.findActiveUserByBlobId(blobId).orElse(null);

        if (user == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        return BlobApiResponse.success(BlobUserResponse.from(user));
    }

    @Override
    public BlobApiResponse<Void> deleteUser(Long userId) {
        BlobUser user = blobUserRepository.findActiveUserById(userId).orElse(null);

        if (user == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        user.setIsDeleted(true);
        blobUserRepository.save(user);

        return BlobApiResponse.success(null);
    }

    @Override
    @Transactional(readOnly = true)
    public BlobApiResponse<Boolean> checkBlobId(String blobId) {
        boolean exists = blobUserRepository.existsByBlobId(blobId);
        return BlobApiResponse.success(!exists);
    }

    @Override
    public BlobApiResponse<BlobUserResponse> updateProfile(Long userId, MultipartFile profileImage,
            UpdateProfileRequest request) {
        BlobUser user = blobUserRepository.findActiveUserById(userId).orElse(null);

        if (user == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        if (request.getNickname() != null) {
            user.setNickname(request.getNickname());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        // TODO: 프로필 이미지 업로드 처리
        if (profileImage != null && !profileImage.isEmpty()) {
            // 파일 업로드 로직 구현 필요
        }

        BlobUser updatedUser = blobUserRepository.save(user);
        return BlobApiResponse.success(BlobUserResponse.from(updatedUser));
    }

    @Override
    public BlobApiResponse<Void> deleteProfileImage(Long userId) {
        BlobUser user = blobUserRepository.findActiveUserById(userId).orElse(null);

        if (user == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        user.setProfileImageUrl(null);
        blobUserRepository.save(user);

        return BlobApiResponse.success(null);
    }

    @Override
    @Transactional(readOnly = true)
    public BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> getUserPosts(String blobId,
            int page, int size) {
        BlobUser user = blobUserRepository.findActiveUserByBlobId(blobId).orElse(null);

        if (user == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findActivePostsByUserId(user.getUserId(), pageable);

        Page<PostSummaryResponse> responsePage = postPage.map(PostSummaryResponse::from);
        BlobPagedResponse<PostSummaryResponse> pagedResponse = BlobPagedResponse.from(responsePage);

        return BlobApiResponse.success(pagedResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public BlobApiResponse<BlobPagedResponse<PostSummaryResponse>> getUserBookmarks(String blobId,
            int page, int size) {
        BlobUser user = blobUserRepository.findActiveUserByBlobId(blobId).orElse(null);

        if (user == null) {
            return BlobApiResponse.error(404, "사용자를 찾을 수 없습니다", "USER_001");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Bookmark> bookmarkPage = bookmarkRepository.findByUserId(user.getUserId(), pageable);

        Page<PostSummaryResponse> responsePage =
                bookmarkPage.map(bookmark -> PostSummaryResponse.from(bookmark.getPost()));
        BlobPagedResponse<PostSummaryResponse> pagedResponse = BlobPagedResponse.from(responsePage);

        return BlobApiResponse.success(pagedResponse);
    }
}
