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
import java.util.ArrayList;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.rolling.model.ServiceResult;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.dto.Recipient.RecipientDto;
import com.rolling.model.entity.Recipient;
import com.rolling.model.enums.ColorType;
import com.rolling.repository.RecipientRepository;
import com.rolling.service.impl.RecipientServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RecipientServiceTest {

    @Mock
    private RecipientRepository recipientRepository;

    @InjectMocks
    private RecipientServiceImpl recipientService;

    private Recipient recipient;
    private RecipientCreateDto createDto;
    private RecipientDto expectedDto;

    @BeforeEach
    void setUp() {
        // 테스트용 데이터 설정
        recipient = Recipient.builder().id(1L).name("테스트 수신자").backgroundColor(ColorType.BLUE)
                .backgroundImageURL("https://example.com/bg.jpg").createdAt(LocalDateTime.now())
                .build();

        createDto = RecipientCreateDto.builder().name("테스트 수신자").backgroundColor("blue")
                .backgroundImageURL("https://example.com/bg.jpg").build();

        expectedDto = RecipientDto.builder().id(1L).name("테스트 수신자").backgroundColor(ColorType.BLUE)
                .backgroundImageURL("https://example.com/bg.jpg")
                .createdAt(recipient.getCreatedAt()).messageCount(0).reactionCount(0)
                .recentMessages(new ArrayList<>()).topReactions(new ArrayList<>()).build();
    }

    @Test
    @DisplayName("Recipient 생성 테스트 - 새 수신자")
    void createRecipient_NewRecipient_ShouldReturnSuccess() {
        // given
        when(recipientRepository.findFirstByName(any(String.class))).thenReturn(Optional.empty());
        when(recipientRepository.save(any(Recipient.class))).thenReturn(recipient);

        // when
        ServiceResult<RecipientDto> result = recipientService.createRecipient(createDto);

        // then
        assertTrue(result.isSuccess());
        assertEquals(HttpStatus.CREATED, result.getStatus());
        assertEquals(1L, result.getDataOrNull().getId());
        assertEquals("테스트 수신자", result.getDataOrNull().getName());
        assertEquals(ColorType.BLUE, result.getDataOrNull().getBackgroundColor());
    }

    @Test
    @DisplayName("Recipient 생성 테스트 - 이미 존재하는 수신자")
    void createRecipient_ExistingRecipient_ShouldReturnExistingRecipient() {
        // given
        when(recipientRepository.findFirstByName(any(String.class)))
                .thenReturn(Optional.of(recipient));

        // when
        ServiceResult<RecipientDto> result = recipientService.createRecipient(createDto);

        // then
        assertTrue(result.isSuccess());
        assertEquals(HttpStatus.OK, result.getStatus());
        assertEquals(1L, result.getDataOrNull().getId());
        assertEquals("테스트 수신자", result.getDataOrNull().getName());
    }

    @Test
    @DisplayName("Recipient ID로 조회 테스트 - 성공")
    void getRecipientById_ExistingId_ShouldReturnRecipient() {
        // given
        when(recipientRepository.findById(anyLong())).thenReturn(Optional.of(recipient));

        // when
        ServiceResult<RecipientDto> result = recipientService.getRecipientById(1L);

        // then
        assertTrue(result.isSuccess());
        assertEquals(HttpStatus.OK, result.getStatus());
        assertEquals(1L, result.getDataOrNull().getId());
        assertEquals("테스트 수신자", result.getDataOrNull().getName());
    }

    @Test
    @DisplayName("모든 Recipient 조회 테스트")
    void getAllRecipients_ShouldReturnPagedRecipients() {
        // given
        List<Recipient> recipients = List.of(recipient);
        Page<Recipient> recipientPage = new PageImpl<>(recipients, PageRequest.of(0, 8), 1);
        when(recipientRepository.findAll(any(Pageable.class))).thenReturn(recipientPage);

        // when
        ServiceResult<PageResponseDto<RecipientDto>> result =
                recipientService.getAllRecipients(8, 0);

        // then
        assertTrue(result.isSuccess());
        assertEquals(1, result.getDataOrNull().getCount());
        assertEquals(1, result.getDataOrNull().getResults().size());
        assertEquals(1L, result.getDataOrNull().getResults().get(0).getId());
    }

    @Test
    @DisplayName("Recipient 삭제 테스트")
    void deleteRecipient_ShouldDeleteSuccessfully() {
        // given
        when(recipientRepository.findById(anyLong())).thenReturn(Optional.of(recipient));
        doNothing().when(recipientRepository).delete(any(Recipient.class));

        // when
        ServiceResult<Void> result = recipientService.deleteRecipient(1L);

        // then
        assertTrue(result.isSuccess());
        verify(recipientRepository, times(1)).delete(recipient);
    }
}
