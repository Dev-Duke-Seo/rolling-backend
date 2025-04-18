package com.rolling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rolling.entity.Message;
import com.rolling.entity.Reaction;
import com.rolling.entity.Recipient;
import com.rolling.dto.MessagePreviewDto;
import com.rolling.dto.PageResponseDto;
import com.rolling.dto.ReactionPreviewDto;
import com.rolling.dto.RecipientDto;
import com.rolling.exception.ResourceNotFoundException;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.RecipientService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipientServiceImpl implements RecipientService {

        private final RecipientRepository recipientRepository;

        @Autowired
        public RecipientServiceImpl(RecipientRepository recipientRepository) {
                this.recipientRepository = recipientRepository;
        }

        @Override
        public RecipientDto createRecipient(Recipient recipient) {
                Recipient savedRecipient = recipientRepository.save(recipient);
                return convertToDto(savedRecipient);
        }

        @Override
        public RecipientDto getRecipientById(Long id) {
                Recipient recipient = recipientRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found with id: " + id));
                return convertToDto(recipient);
        }

        @Override
        public PageResponseDto<RecipientDto> getAllRecipients(int limit, int offset) {
                Pageable pageable = PageRequest.of(offset / limit, limit);
                Page<Recipient> recipientsPage = recipientRepository.findAll(pageable);

                List<RecipientDto> recipients = recipientsPage.getContent().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());

                String nextUrl = null;
                String prevUrl = null;

                if (recipientsPage.hasNext()) {
                        nextUrl = "/recipients/?limit=" + limit + "&offset=" + (offset + limit);
                }

                if (offset > 0) {
                        prevUrl = "/recipients/?limit=" + limit + "&offset=" + Math.max(0, offset - limit);
                }

                return PageResponseDto.<RecipientDto>builder()
                                .count((int) recipientsPage.getTotalElements())
                                .next(nextUrl)
                                .previous(prevUrl)
                                .results(recipients)
                                .build();
        }

        @Override
        public void deleteRecipient(Long id) {
                Recipient recipient = recipientRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found with id: " + id));
                recipientRepository.delete(recipient);
        }

        private RecipientDto convertToDto(Recipient recipient) {
                List<Message> recentMessages = recipient.getMessages().stream()
                                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                                .limit(3)
                                .collect(Collectors.toList());

                List<MessagePreviewDto> messagePreviewDtos = recentMessages.stream()
                                .map(this::convertToMessagePreviewDto)
                                .collect(Collectors.toList());

                List<Reaction> topReactions = recipient.getReactions().stream()
                                .sorted(Comparator.comparing(Reaction::getCount).reversed())
                                .limit(3)
                                .collect(Collectors.toList());

                List<ReactionPreviewDto> reactionPreviewDtos = topReactions.stream()
                                .map(this::convertToReactionPreviewDto)
                                .collect(Collectors.toList());

                return RecipientDto.builder()
                                .id(recipient.getId())
                                .name(recipient.getName())
                                .backgroundColor(recipient.getBackgroundColor())
                                .backgroundImageURL(recipient.getBackgroundImageURL())
                                .createdAt(recipient.getCreatedAt())
                                .messageCount(recipient.getMessages().size())
                                .recentMessages(messagePreviewDtos)
                                .reactionCount(recipient.getReactions().stream()
                                                .mapToInt(Reaction::getCount)
                                                .sum())
                                .topReactions(reactionPreviewDtos)
                                .build();
        }

        private MessagePreviewDto convertToMessagePreviewDto(Message message) {
                return MessagePreviewDto.builder()
                                .id(message.getId())
                                .sender(message.getSender())
                                .content(message.getContent())
                                .createdAt(message.getCreatedAt())
                                .profileImageURL(message.getProfileImageURL())
                                .relationship(message.getRelationship())
                                .build();
        }

        private ReactionPreviewDto convertToReactionPreviewDto(Reaction reaction) {
                return ReactionPreviewDto.builder()
                                .id(reaction.getId())
                                .emoji(reaction.getEmoji())
                                .count(reaction.getCount())
                                .build();
        }
}
