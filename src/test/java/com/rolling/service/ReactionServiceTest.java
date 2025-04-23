package com.rolling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.rolling.model.ServiceResult;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.ReactionCreateDto;
import com.rolling.model.dto.ReactionDto;
import com.rolling.model.entity.Reaction;
import com.rolling.model.entity.Recipient;
import com.rolling.model.enums.ColorType;
import com.rolling.repository.ReactionRepository;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.impl.ReactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReactionServiceTest {

        @Mock
        private ReactionRepository reactionRepository;

        @Mock
        private RecipientRepository recipientRepository;

        @InjectMocks
        private ReactionServiceImpl reactionService;

        private Recipient recipient;
        private Reaction reaction;
        private ReactionDto reactionDto;
        private ReactionCreateDto reactionCreateDto;

        @BeforeEach
        void setUp() {
                // 테스트 데이터 설정
                recipient = Recipient.builder().id(1L).name("테스트 수신자")
                                .backgroundColor(ColorType.BLUE)
                                .backgroundImageURL("https://example.com/bg.jpg")
                                .createdAt(LocalDateTime.now()).build();

                reaction = Reaction.builder().id(1L).recipient(recipient).emoji("👍").count(5)
                                .build();

                reactionDto = ReactionDto.builder().id(1L).recipientId(1L).emoji("👍").count(5)
                                .build();

                reactionCreateDto =
                                ReactionCreateDto.builder().emoji("👍").type("increase").build();
        }

        @Test
        @DisplayName("새 반응 추가 테스트")
        void addReaction_NewReaction_ShouldCreateReaction() {
                // given
                when(recipientRepository.findById(anyLong())).thenReturn(Optional.of(recipient));
                when(reactionRepository.findByRecipientIdAndEmoji(anyLong(), any(String.class)))
                                .thenReturn(Optional.empty());
                when(reactionRepository.save(any(Reaction.class))).thenReturn(reaction);

                // when
                ServiceResult<ReactionDto> result =
                                reactionService.addReaction(1L, reactionCreateDto);

                // then
                assertTrue(result.isSuccess());
                assertEquals(1L, result.getDataOrNull().getId());
                assertEquals("👍", result.getDataOrNull().getEmoji());
                assertEquals(5, result.getDataOrNull().getCount());
        }

        @Test
        @DisplayName("기존 반응 증가 테스트")
        void addReaction_ExistingReaction_ShouldIncreaseCount() {
                // given
                Reaction existingReaction = Reaction.builder().id(1L).recipient(recipient)
                                .emoji("👍").count(4).build();

                Reaction increasedReaction = Reaction.builder().id(1L).recipient(recipient)
                                .emoji("👍").count(5).build();


                when(recipientRepository.findById(anyLong())).thenReturn(Optional.of(recipient));
                when(reactionRepository.findByRecipientIdAndEmoji(anyLong(), any(String.class)))
                                .thenReturn(Optional.of(existingReaction));
                when(reactionRepository.save(any(Reaction.class))).thenReturn(increasedReaction);

                // when
                ServiceResult<ReactionDto> result =
                                reactionService.addReaction(1L, reactionCreateDto);

                // then
                assertTrue(result.isSuccess());
                assertEquals(1L, result.getDataOrNull().getId());
                assertEquals("👍", result.getDataOrNull().getEmoji());
                assertEquals(5, result.getDataOrNull().getCount());
        }

        @Test
        @DisplayName("기존 반응 감소 테스트")
        void addReaction_ExistingReaction_ShouldDecreaseCount() {
                // given
                ReactionCreateDto decreaseDto =
                                ReactionCreateDto.builder().emoji("👍").type("decrease").build();

                Reaction existingReaction = Reaction.builder().id(1L).recipient(recipient)
                                .emoji("👍").count(5).build();

                Reaction decreasedReaction = Reaction.builder().id(1L).recipient(recipient)
                                .emoji("👍").count(4).build();

                when(recipientRepository.findById(anyLong())).thenReturn(Optional.of(recipient));
                when(reactionRepository.findByRecipientIdAndEmoji(anyLong(), any(String.class)))
                                .thenReturn(Optional.of(existingReaction));
                when(reactionRepository.save(any(Reaction.class))).thenReturn(decreasedReaction);

                // when
                ServiceResult<ReactionDto> result = reactionService.addReaction(1L, decreaseDto);

                // then
                assertTrue(result.isSuccess());
                assertEquals(1L, result.getDataOrNull().getId());
                assertEquals("👍", result.getDataOrNull().getEmoji());
                assertEquals(4, result.getDataOrNull().getCount());
        }

        @Test
        @DisplayName("수신자 ID로 반응 목록 조회 테스트")
        void getReactionsByRecipientId_ShouldReturnPagedReactions() {
                // given
                List<Reaction> reactions = List.of(reaction);
                Page<Reaction> reactionPage = new PageImpl<>(reactions);

                when(recipientRepository.existsById(anyLong())).thenReturn(true);
                when(reactionRepository.findByRecipientId(anyLong(), any(Pageable.class)))
                                .thenReturn(reactionPage);

                // when
                ServiceResult<PageResponseDto<ReactionDto>> result =
                                reactionService.getReactionsByRecipientId(1L, 10, 0);

                // then
                assertTrue(result.isSuccess());
                assertEquals(1, result.getDataOrNull().getCount());
                assertEquals(1, result.getDataOrNull().getResults().size());
                assertEquals(1L, result.getDataOrNull().getResults().get(0).getId());
                assertEquals("👍", result.getDataOrNull().getResults().get(0).getEmoji());
                assertEquals(5, result.getDataOrNull().getResults().get(0).getCount());
        }
}
