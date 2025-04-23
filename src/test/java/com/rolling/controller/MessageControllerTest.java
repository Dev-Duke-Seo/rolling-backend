package com.rolling.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rolling.dto.MessageDto;
import com.rolling.dto.PageResponseDto;
import com.rolling.entity.Message;
import com.rolling.entity.Recipient;
import com.rolling.exception.ResourceNotFoundException;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private MessageService messageService;

        @MockitoBean
        private RecipientRepository recipientRepository;

        @Autowired
        private ObjectMapper objectMapper;

        private Recipient recipient;
        private Message message;
        private MessageDto messageDto;

        @BeforeEach
        void setUp() {
                recipient = Recipient.builder()
                                .id(1L)
                                .name("테스트수신자")
                                .build();

                message = Message.builder()
                                .id(1L)
                                .recipient(recipient)
                                .sender("테스트발신자")
                                .profileImageURL("https://example.com/profile.jpg")
                                .backgroundColor("#FFEEEE")
                                .relationship("친구")
                                .content("테스트 메시지 내용입니다.")
                                .font("Arial")
                                .createdAt(LocalDateTime.now())
                                .build();

                messageDto = MessageDto.builder()
                                .id(1L)
                                .recipientId(1L)
                                .sender("테스트발신자")
                                .profileImageURL("https://example.com/profile.jpg")
                                .backgroundColor("#FFEEEE")
                                .relationship("친구")
                                .content("테스트 메시지 내용입니다.")
                                .font("Arial")
                                .createdAt(message.getCreatedAt())
                                .build();
        }

        @Test
        void createMessage_ShouldReturnCreatedMessage() throws Exception {
                when(recipientRepository.findById(1L)).thenReturn(Optional.of(recipient));
                when(messageService.createMessage(any(Message.class))).thenReturn(messageDto);

                mockMvc.perform(post("/recipients/1/messages/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(message)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id", is(1)))
                                .andExpect(jsonPath("$.recipientId", is(1)))
                                .andExpect(jsonPath("$.sender", is("테스트발신자")))
                                .andExpect(jsonPath("$.content", is("테스트 메시지 내용입니다.")));
        }

        @Test
        void createMessage_WithNonExistingRecipient_ShouldReturnNotFound() throws Exception {
                when(recipientRepository.findById(999L)).thenReturn(Optional.empty());

                mockMvc.perform(post("/recipients/999/messages/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(message)))
                                .andExpect(status().isNotFound());
        }

        @Test
        void getMessageById_ShouldReturnMessage() throws Exception {
                when(messageService.getMessageById(1L)).thenReturn(messageDto);

                mockMvc.perform(get("/recipients/1/messages/1/"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(1)))
                                .andExpect(jsonPath("$.recipientId", is(1)))
                                .andExpect(jsonPath("$.sender", is("테스트발신자")));
        }

        @Test
        void getMessageById_WithIncorrectRecipientId_ShouldReturnNotFound() throws Exception {
                MessageDto messageWithDifferentRecipient = MessageDto.builder()
                                .id(1L)
                                .recipientId(2L) // 다른 수신자 ID
                                .sender("테스트발신자")
                                .content("테스트 메시지")
                                .build();

                when(messageService.getMessageById(1L)).thenReturn(messageWithDifferentRecipient);

                mockMvc.perform(get("/recipients/1/messages/1/"))
                                .andExpect(status().isNotFound());
        }

        @Test
        void getMessagesByRecipientId_ShouldReturnMessagesPage() throws Exception {
                MessageDto message2 = MessageDto.builder()
                                .id(2L)
                                .recipientId(1L)
                                .sender("다른발신자")
                                .content("다른 메시지 내용")
                                .build();

                PageResponseDto<MessageDto> pageResponse = PageResponseDto.<MessageDto>builder()
                                .count(2)
                                .next(null)
                                .previous(null)
                                .results(Arrays.asList(messageDto, message2))
                                .build();

                when(messageService.getMessagesByRecipientId(1L, 8, 0)).thenReturn(pageResponse);

                mockMvc.perform(get("/recipients/1/messages/")
                                .param("limit", "8")
                                .param("offset", "0"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.count", is(2)))
                                .andExpect(jsonPath("$.results[0].id", is(1)))
                                .andExpect(jsonPath("$.results[1].id", is(2)));
        }

        @Test
        void updateMessage_ShouldReturnUpdatedMessage() throws Exception {
                Message updatedMessage = Message.builder()
                                .id(1L)
                                .recipient(recipient)
                                .sender("업데이트발신자")
                                .content("업데이트된 메시지 내용")
                                .build();

                MessageDto updatedMessageDto = MessageDto.builder()
                                .id(1L)
                                .recipientId(1L)
                                .sender("업데이트발신자")
                                .content("업데이트된 메시지 내용")
                                .build();

                when(messageService.getMessageById(1L)).thenReturn(messageDto);
                when(messageService.updateMessage(anyLong(), any(Message.class))).thenReturn(updatedMessageDto);

                mockMvc.perform(put("/recipients/1/messages/1/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updatedMessage)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.sender", is("업데이트발신자")))
                                .andExpect(jsonPath("$.content", is("업데이트된 메시지 내용")));
        }

        @Test
        void deleteMessage_ShouldReturnNoContent() throws Exception {
                when(messageService.getMessageById(1L)).thenReturn(messageDto);
                doNothing().when(messageService).deleteMessage(1L);

                mockMvc.perform(delete("/recipients/1/messages/1/"))
                                .andExpect(status().isNoContent());
        }

        @Test
        void deleteMessage_WithIncorrectRecipientId_ShouldReturnNotFound() throws Exception {
                MessageDto messageWithDifferentRecipient = MessageDto.builder()
                                .id(1L)
                                .recipientId(2L) // 다른 수신자 ID
                                .build();

                when(messageService.getMessageById(1L)).thenReturn(messageWithDifferentRecipient);

                mockMvc.perform(delete("/recipients/1/messages/1/"))
                                .andExpect(status().isNotFound());
        }
}