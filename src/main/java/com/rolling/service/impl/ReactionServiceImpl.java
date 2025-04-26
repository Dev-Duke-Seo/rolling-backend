package com.rolling.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rolling.exception.ServiceError;
import com.rolling.model.ServiceResult;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.ReactionCreateDto;
import com.rolling.model.dto.ReactionDto;
import com.rolling.model.entity.Reaction;
import com.rolling.model.entity.Recipient;
import com.rolling.repository.ReactionRepository;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.ReactionService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

        private final ReactionRepository reactionRepository;
        private final RecipientRepository recipientRepository;

        @Override
        @Transactional
        public ServiceResult<ReactionDto> addReaction(Long recipientId,
                        ReactionCreateDto reactionCreateDto) {
                Recipient recipient = recipientRepository.findById(recipientId)
                                .orElseThrow(() -> ServiceError.recipientNotFound(recipientId));

                Optional<Reaction> existingReaction = reactionRepository.findByRecipientIdAndEmoji(
                                recipientId, reactionCreateDto.getEmoji());

                Reaction reaction;

                if (existingReaction.isPresent()) {
                        reaction = existingReaction.get();

                        if ("increase".equals(reactionCreateDto.getType())) {
                                reaction.setCount(reaction.getCount() + 1);
                        } else if ("decrease".equals(reactionCreateDto.getType())
                                        && reaction.getCount() > 0) {
                                reaction.setCount(reaction.getCount() - 1);
                        }
                } else {
                        reaction = Reaction.builder().recipient(recipient)
                                        .emoji(reactionCreateDto.getEmoji())
                                        .count("increase".equals(reactionCreateDto.getType()) ? 1
                                                        : 0)
                                        .build();
                }

                Reaction savedReaction = reactionRepository.save(reaction);
                return ServiceResult.<ReactionDto>builder().isSuccess(true)
                                .message("Reaction added successfully")
                                .data(convertToDto(savedReaction)).build();
        }

        @Override
        @Transactional(readOnly = true)
        public ServiceResult<PageResponseDto<ReactionDto>> getReactionsByRecipientId(
                        Long recipientId, int limit, int offset) {
                if (!recipientRepository.existsById(recipientId)) {
                        throw ServiceError.recipientNotFound(recipientId);
                }

                Pageable pageable = PageRequest.of(offset / limit, limit,
                                Sort.by(Sort.Direction.DESC, "count"));
                Page<Reaction> reactionsPage =
                                reactionRepository.findByRecipientId(recipientId, pageable);

                List<ReactionDto> reactions = reactionsPage.getContent().stream()
                                .map(this::convertToDto).collect(Collectors.toList());

                String nextUrl = null;
                String prevUrl = null;

                if (reactionsPage.hasNext()) {
                        nextUrl = "/recipients/" + recipientId + "/reactions/?limit=" + limit
                                        + "&offset=" + (offset + limit);
                }

                if (offset > 0) {
                        prevUrl = "/recipients/" + recipientId + "/reactions/?limit=" + limit
                                        + "&offset=" + Math.max(0, offset - limit);
                }

                return ServiceResult.<PageResponseDto<ReactionDto>>builder().isSuccess(true)
                                .message("Reactions fetched successfully")
                                .data(PageResponseDto.<ReactionDto>builder()
                                                .count((int) reactionsPage.getTotalElements())
                                                .next(nextUrl).previous(prevUrl).results(reactions)
                                                .build())
                                .build();
        }

        private ReactionDto convertToDto(Reaction reaction) {
                return ReactionDto.builder().id(reaction.getId())
                                .recipientId(reaction.getRecipient().getId())
                                .emoji(reaction.getEmoji()).count(reaction.getCount()).build();
        }
}
