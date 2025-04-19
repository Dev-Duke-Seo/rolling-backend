package com.rolling.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rolling.dto.MessagePreviewDto;
import com.rolling.dto.PageResponseDto;
import com.rolling.dto.ReactionPreviewDto;
import com.rolling.dto.RecipientDto;
import com.rolling.exception.ResourceNotFoundException;
import com.rolling.model.ServiceResult;
import com.rolling.model.entity.Message;
import com.rolling.model.entity.Reaction;
import com.rolling.model.entity.Recipient;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.RecipientService;
import lombok.RequiredArgsConstructor;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipientServiceImpl implements RecipientService {

        private final RecipientRepository recipientRepository;


        @Override
        @Transactional
        public ServiceResult<RecipientDto> createRecipient(Recipient recipient) {
                Recipient savedRecipient = recipientRepository.save(recipient);

                return ServiceResult.success("Recipient created successfully",
                                toDto(savedRecipient), HttpStatus.CREATED);
        }

        @Override
        public ServiceResult<RecipientDto> getRecipientById(Long id) {
                Recipient recipient = recipientRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Recipient not found with id: " + id));
                return ServiceResult.<RecipientDto>builder().isSuccess(true)
                                .message("Recipient found successfully").data(toDto(recipient))
                                .build();
        }

        @Override
        public ServiceResult<PageResponseDto<RecipientDto>> getAllRecipients(int limit,
                        int offset) {
                Pageable pageable = PageRequest.of(offset / limit, limit);
                Page<Recipient> recipientsPage = recipientRepository.findAll(pageable);

                List<RecipientDto> recipients = recipientsPage.getContent().stream()
                                .map(this::toDto).collect(Collectors.toList());

                String nextUrl = null;
                String prevUrl = null;

                if (recipientsPage.hasNext()) {
                        nextUrl = "/recipients/?limit=" + limit + "&offset=" + (offset + limit);
                }

                if (offset > 0) {
                        prevUrl = "/recipients/?limit=" + limit + "&offset="
                                        + Math.max(0, offset - limit);
                }

                return ServiceResult.<PageResponseDto<RecipientDto>>builder().isSuccess(true)
                                .message("Recipients fetched successfully")
                                .data(PageResponseDto.<RecipientDto>builder()
                                                .count((int) recipientsPage.getTotalElements())
                                                .next(nextUrl).previous(prevUrl).results(recipients)
                                                .build())
                                .build();
        }

        @Override
        public ServiceResult<Void> deleteRecipient(Long id) {
                Recipient recipient = recipientRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Recipient not found with id: " + id));
                recipientRepository.delete(recipient);
                return ServiceResult.<Void>builder().isSuccess(true)
                                .message("Recipient deleted successfully").build();
        }

        private RecipientDto toDto(Recipient recipient) {
                List<Message> recentMessages = recipient.getMessages().stream()
                                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                                .limit(3).collect(Collectors.toList());

                List<MessagePreviewDto> messagePreviewDtos = recentMessages.stream()
                                .map(this::convertToMessagePreviewDto).collect(Collectors.toList());

                List<Reaction> topReactions = recipient.getReactions().stream()
                                .sorted(Comparator.comparing(Reaction::getCount).reversed())
                                .limit(3).collect(Collectors.toList());

                List<ReactionPreviewDto> reactionPreviewDtos =
                                topReactions.stream().map(this::convertToReactionPreviewDto)
                                                .collect(Collectors.toList());

                return RecipientDto.builder().id(recipient.getId()).name(recipient.getName())
                                .backgroundColor(recipient.getBackgroundColor())
                                .backgroundImageURL(recipient.getBackgroundImageURL())
                                .createdAt(recipient.getCreatedAt())
                                .messageCount(recipient.getMessages().size())
                                .recentMessages(messagePreviewDtos)
                                .reactionCount(recipient.getReactions().stream()
                                                .mapToInt(Reaction::getCount).sum())
                                .topReactions(reactionPreviewDtos).build();
        }

        private MessagePreviewDto convertToMessagePreviewDto(Message message) {
                return MessagePreviewDto.builder().id(message.getId()).sender(message.getSender())
                                .content(message.getContent()).createdAt(message.getCreatedAt())
                                .profileImageURL(message.getProfileImageURL())
                                .relationship(message.getRelationship()).build();
        }

        private ReactionPreviewDto convertToReactionPreviewDto(Reaction reaction) {
                return ReactionPreviewDto.builder().id(reaction.getId()).emoji(reaction.getEmoji())
                                .count(reaction.getCount()).build();
        }
}
