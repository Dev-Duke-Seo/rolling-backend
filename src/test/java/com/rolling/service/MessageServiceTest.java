package com.rolling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.rolling.model.dto.MessageDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.entity.Message;
import com.rolling.model.entity.Recipient;
import com.rolling.model.enums.ColorType;
import com.rolling.repository.MessageRepository;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.impl.MessageServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private RecipientRepository recipientRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    private Recipient recipient;
    private Message message;
    private MessageDto messageDto;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 설정
        recipient = Recipient.builder().id(1L).name("테스트 수신자").backgroundColor(ColorType.BLUE)
                .backgroundImageURL("https://example.com/bg.jpg").createdAt(LocalDateTime.now())
                .build();

        message = Message.builder().id(1L).recipient(recipient).sender("테스트 발신자")
                .content("테스트 메시지 내용").relationship("친구")
                .backgroundColor(ColorType.PURPLE.getValue()).font("Arial")
                .profileImageURL("https://example.com/profile.jpg").createdAt(LocalDateTime.now())
                .build();

        messageDto = MessageDto.builder().id(1L).recipientId(1L).sender("테스트 발신자")
                .content("테스트 메시지 내용").relationship("친구").backgroundColor(ColorType.PURPLE)
                .font("Arial").profileImageURL("https://example.com/profile.jpg")
                .createdAt(message.getCreatedAt()).build();
    }

    @Test
    @DisplayName("메시지 생성 테스트")
    void createMessage_ShouldReturnCreatedMessage() {
        // given
        when(recipientRepository.findById(anyLong())).thenReturn(Optional.of(recipient));
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        // when
        ServiceResult<MessageDto> result = messageService.createMessage(message, 1L);

        // then
        assertTrue(result.isSuccess());
        assertEquals(1L, result.getDataOrNull().getId());
        assertEquals("테스트 발신자", result.getDataOrNull().getSender());
        assertEquals("테스트 메시지 내용", result.getDataOrNull().getContent());
        assertEquals(1L, result.getDataOrNull().getRecipientId());
    }

    @Test
    @DisplayName("메시지 ID로 조회 테스트")
    void getMessageById_ShouldReturnMessage() {
        // given
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(message));

        // when
        ServiceResult<MessageDto> result = messageService.getMessageById(1L);

        // then
        assertTrue(result.isSuccess());
        assertEquals(1L, result.getDataOrNull().getId());
        assertEquals("테스트 발신자", result.getDataOrNull().getSender());
        assertEquals("테스트 메시지 내용", result.getDataOrNull().getContent());
    }

    @Test
    @DisplayName("수신자 ID로 메시지 목록 조회 테스트")
    void getMessagesByRecipientId_ShouldReturnPagedMessages() {
        // given
        List<Message> messages = List.of(message);
        Page<Message> messagePage = new PageImpl<>(messages);

        when(recipientRepository.existsById(anyLong())).thenReturn(true);
        when(messageRepository.findByRecipientId(anyLong(), any(Pageable.class)))
                .thenReturn(messagePage);

        // when
        ServiceResult<PageResponseDto<MessageDto>> result =
                messageService.getMessagesByRecipientId(1L, 10, 0);

        // then
        assertTrue(result.isSuccess());
        assertEquals(1, result.getDataOrNull().getCount());
        assertEquals(1, result.getDataOrNull().getResults().size());
        assertEquals(1L, result.getDataOrNull().getResults().get(0).getId());
        assertEquals("테스트 발신자", result.getDataOrNull().getResults().get(0).getSender());
    }

    @Test
    @DisplayName("메시지 업데이트 테스트")
    void updateMessage_ShouldReturnUpdatedMessage() {
        // given
        Message updatedMessage = Message.builder().id(1L).recipient(recipient).sender("업데이트된 발신자")
                .content("업데이트된 내용").relationship("가족").backgroundColor(ColorType.GREEN.getValue())
                .font("Verdana").profileImageURL("https://example.com/updated.jpg")
                .createdAt(message.getCreatedAt()).build();

        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(message));
        when(messageRepository.save(any(Message.class))).thenReturn(updatedMessage);

        // when
        ServiceResult<MessageDto> result = messageService.updateMessage(1L, updatedMessage);

        // then
        assertTrue(result.isSuccess());
        assertEquals(1L, result.getDataOrNull().getId());
        assertEquals("업데이트된 발신자", result.getDataOrNull().getSender());
        assertEquals("업데이트된 내용", result.getDataOrNull().getContent());
        assertEquals("가족", result.getDataOrNull().getRelationship());
    }

    @Test
    @DisplayName("메시지 삭제 테스트")
    void deleteMessage_ShouldDeleteSuccessfully() {
        // given
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(message));
        doNothing().when(messageRepository).delete(any(Message.class));

        // when
        ServiceResult<Void> result = messageService.deleteMessage(1L);

        // then
        assertTrue(result.isSuccess());
        verify(messageRepository, times(1)).delete(message);
    }
}
