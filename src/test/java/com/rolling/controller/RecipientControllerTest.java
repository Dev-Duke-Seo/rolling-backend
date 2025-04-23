package com.rolling.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rolling.model.ServiceResult;
import com.rolling.model.dto.MessageDto;
import com.rolling.model.dto.MessagePreviewDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.ReactionCreateDto;
import com.rolling.model.dto.ReactionDto;
import com.rolling.model.dto.ReactionPreviewDto;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.dto.Recipient.RecipientDto;
import com.rolling.model.entity.Message;
import com.rolling.model.enums.ColorType;
import com.rolling.service.MessageService;
import com.rolling.service.ReactionService;
import com.rolling.service.RecipientService;

@WebMvcTest(RecipientController.class)
public class RecipientControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private RecipientService recipientService;

        @MockBean
        private MessageService messageService;

        @MockBean
        private ReactionService reactionService;

        @Autowired
        private ObjectMapper objectMapper;

        private RecipientDto recipientDto;
        private RecipientCreateDto createDto;
        private MessageDto messageDto;
        private Message message;
        private PageResponseDto<RecipientDto> recipientPageDto;
        private PageResponseDto<MessageDto> messagePageDto;
        private ReactionDto reactionDto;
        private ReactionCreateDto reactionCreateDto;

        @BeforeEach
        void setUp() {
                // 테스트 데이터 설정
                List<MessagePreviewDto> recentMessages = new ArrayList<>();
                recentMessages.add(MessagePreviewDto.builder().id(1L).sender("테스트 발신자")
                                .content("테스트 내용").createdAt(LocalDateTime.now()).build());

                List<ReactionPreviewDto> topReactions = new ArrayList<>();
                topReactions.add(ReactionPreviewDto.builder().id(1L).emoji("👍").count(5).build());

                recipientDto = RecipientDto.builder().id(1L).name("테스트 수신자")
                                .backgroundColor(ColorType.BLUE)
                                .backgroundImageURL("https://example.com/bg.jpg")
                                .createdAt(LocalDateTime.now()).messageCount(10).reactionCount(20)
                                .recentMessages(recentMessages).topReactions(topReactions).build();

                createDto = RecipientCreateDto.builder().name("테스트 수신자").backgroundColor("blue")
                                .backgroundImageURL("https://example.com/bg.jpg").build();

                messageDto = MessageDto.builder().id(1L).recipientId(1L).sender("테스트 발신자")
                                .content("테스트 내용").relationship("친구")
                                .backgroundColor(ColorType.BLUE).font("Arial")
                                .profileImageURL("https://example.com/profile.jpg")
                                .createdAt(LocalDateTime.now()).build();

                message = Message.builder().sender("테스트 발신자").content("테스트 내용").relationship("친구")
                                .backgroundColor("blue").font("Arial")
                                .profileImageURL("https://example.com/profile.jpg").build();

                List<RecipientDto> recipients = List.of(recipientDto);
                recipientPageDto = PageResponseDto.<RecipientDto>builder().count(1)
                                .results(recipients).build();

                List<MessageDto> messages = List.of(messageDto);
                messagePageDto = PageResponseDto.<MessageDto>builder().count(1).results(messages)
                                .build();

                reactionDto = ReactionDto.builder().id(1L).recipientId(1L).emoji("👍").count(1)
                                .build();

                reactionCreateDto =
                                ReactionCreateDto.builder().emoji("👍").type("increase").build();
        }

        @Test
        @DisplayName("모든 Recipient 조회 테스트")
        void getAllRecipients() throws Exception {
                // given
                when(recipientService.getAllRecipients(anyInt(), anyInt()))
                                .thenReturn(ServiceResult.success(recipientPageDto));

                // when & then
                mockMvc.perform(get("/recipients/").param("limit", "8").param("offset", "0"))
                                .andExpect(status().isOk()).andExpect(jsonPath("$.count").value(1))
                                .andExpect(jsonPath("$.results[0].id").value(1))
                                .andExpect(jsonPath("$.results[0].name").value("테스트 수신자"));
        }

        @Test
        @DisplayName("Recipient 생성 테스트")
        void createRecipient() throws Exception {
                // given
                when(recipientService.createRecipient(any(RecipientCreateDto.class)))
                                .thenReturn(ServiceResult.success("Recipient created successfully",
                                                recipientDto, HttpStatus.CREATED));

                // when & then
                mockMvc.perform(post("/recipients/").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("테스트 수신자"));
        }

        @Test
        @DisplayName("ID로 Recipient 조회 테스트")
        void getRecipientById() throws Exception {
                // given
                when(recipientService.getRecipientById(anyLong()))
                                .thenReturn(ServiceResult.success(recipientDto));

                // when & then
                mockMvc.perform(get("/recipients/1/")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.name").value("테스트 수신자"))
                                .andExpect(jsonPath("$.messageCount").value(10))
                                .andExpect(jsonPath("$.reactionCount").value(20));
        }

        @Test
        @DisplayName("Recipient 삭제 테스트")
        void deleteRecipient() throws Exception {
                // given
                when(recipientService.deleteRecipient(anyLong()))
                                .thenReturn(ServiceResult.<Void>builder().isSuccess(true).build());

                // when & then
                mockMvc.perform(delete("/recipients/1/")).andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("메시지 생성 테스트")
        void createMessage() throws Exception {
                // given
                when(messageService.createMessage(any(Message.class), anyLong()))
                                .thenReturn(ServiceResult.success(messageDto));

                // when & then
                mockMvc.perform(post("/recipients/1/messages/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(message)))
                                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.sender").value("테스트 발신자"))
                                .andExpect(jsonPath("$.content").value("테스트 내용"));
        }

        @Test
        @DisplayName("Recipient의 메시지 목록 조회 테스트")
        void getMessages() throws Exception {
                // given
                when(messageService.getMessagesByRecipientId(anyLong(), anyInt(), anyInt()))
                                .thenReturn(ServiceResult.success(messagePageDto));

                // when & then
                mockMvc.perform(get("/recipients/1/messages/").param("limit", "8").param("offset",
                                "0")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.count").value(1))
                                .andExpect(jsonPath("$.results[0].id").value(1))
                                .andExpect(jsonPath("$.results[0].sender").value("테스트 발신자"));
        }

        @Test
        @DisplayName("반응 추가 테스트")
        void createReaction() throws Exception {
                // given
                when(reactionService.addReaction(anyLong(), any(ReactionCreateDto.class)))
                                .thenReturn(ServiceResult.success(reactionDto));

                // when & then
                mockMvc.perform(post("/recipients/1/reactions/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(reactionCreateDto)))
                                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.emoji").value("👍"))
                                .andExpect(jsonPath("$.count").value(1));
        }
}
