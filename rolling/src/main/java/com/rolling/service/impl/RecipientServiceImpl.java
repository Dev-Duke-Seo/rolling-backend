package com.rolling.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rolling.exception.ResourceNotFoundException;
import com.rolling.model.ServiceResult;
import com.rolling.model.dto.MessagePreviewDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.ReactionPreviewDto;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.dto.Recipient.RecipientDto;
import com.rolling.model.entity.Message;
import com.rolling.model.entity.Reaction;
import com.rolling.model.entity.Recipient;
import com.rolling.model.enums.ColorType;
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
        public ServiceResult<RecipientDto> createRecipient(RecipientCreateDto recipient) {
                Recipient entity = Recipient.builder().name(recipient.getName())
                                .backgroundColor(
                                                ColorType.fromValue(recipient.getBackgroundColor()))
                                .backgroundImageURL(recipient.getBackgroundImageURL()).build();
                Recipient savedRecipient = recipientRepository.save(entity);

                return ServiceResult.success("Recipient created successfully",
                                toDto(savedRecipient), HttpStatus.CREATED);
        }

        @Override
        public ServiceResult<RecipientDto> getRecipientById(Long id) {
                Recipient recipient = recipientRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Recipient not found with id: " + id));
                System.out.println("recipient: " + recipient);

                return ServiceResult.success("Recipient found successfully", toDto(recipient),
                                HttpStatus.OK);
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
                                .backgroundColor(String.valueOf(recipient.getBackgroundColor()))
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
