package com.rolling.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rolling.dto.PageResponseDto;
import com.rolling.dto.RecipientDto;
import com.rolling.entity.Recipient;
import com.rolling.service.RecipientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipientController.class)
public class RecipientControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private RecipientService recipientService;

        @Autowired
        private ObjectMapper objectMapper;

        private Recipient recipient;
        private RecipientDto recipientDto;

        @BeforeEach
        void setUp() {
                recipient = Recipient.builder()
                                .id(1L)
                                .name("테스트수신자")
                                .backgroundColor("#FFFFFF")
                                .backgroundImageURL("https://example.com/image.jpg")
                                .createdAt(LocalDateTime.now())
                                .build();

                recipientDto = RecipientDto.builder()
                                .id(1L)
                                .name("테스트수신자")
                                .backgroundColor("#FFFFFF")
                                .backgroundImageURL("https://example.com/image.jpg")
                                .createdAt(recipient.getCreatedAt())
                                .messageCount(0)
                                .build();
        }

        @Test
        void createRecipient_ShouldReturnCreatedRecipient() throws Exception {
                when(recipientService.createRecipient(any(Recipient.class))).thenReturn(recipientDto);

                mockMvc.perform(post("/recipients/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(recipient)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id", is(1)))
                                .andExpect(jsonPath("$.name", is("테스트수신자")))
                                .andExpect(jsonPath("$.backgroundColor", is("#FFFFFF")))
                                .andExpect(jsonPath("$.backgroundImageURL", is("https://example.com/image.jpg")));
        }

        @Test
        void getRecipientById_ShouldReturnRecipient() throws Exception {
                when(recipientService.getRecipientById(1L)).thenReturn(recipientDto);

                mockMvc.perform(get("/recipients/1/"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(1)))
                                .andExpect(jsonPath("$.name", is("테스트수신자")));
        }

        @Test
        void getAllRecipients_ShouldReturnRecipientsPage() throws Exception {
                RecipientDto recipient2 = RecipientDto.builder()
                                .id(2L)
                                .name("다른수신자")
                                .backgroundColor("#000000")
                                .backgroundImageURL("https://example.com/other.jpg")
                                .messageCount(5)
                                .build();

                PageResponseDto<RecipientDto> pageResponse = PageResponseDto.<RecipientDto>builder()
                                .count(2)
                                .next(null)
                                .previous(null)
                                .results(Arrays.asList(recipientDto, recipient2))
                                .build();

                when(recipientService.getAllRecipients(8, 0)).thenReturn(pageResponse);

                mockMvc.perform(get("/recipients/")
                                .param("limit", "8")
                                .param("offset", "0"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.count", is(2)))
                                .andExpect(jsonPath("$.results[0].id", is(1)))
                                .andExpect(jsonPath("$.results[1].id", is(2)));
        }

        @Test
        void deleteRecipient_ShouldReturnNoContent() throws Exception {
                doNothing().when(recipientService).deleteRecipient(anyLong());

                mockMvc.perform(delete("/recipients/1/"))
                                .andExpect(status().isNoContent());
        }
}