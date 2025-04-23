package com.rolling.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rolling.model.ServiceResult;
import com.rolling.model.dto.MessageDto;
import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.ReactionCreateDto;
import com.rolling.model.dto.ReactionDto;
import com.rolling.model.dto.Recipient.RecipientCreateDto;
import com.rolling.model.dto.Recipient.RecipientDto;
import com.rolling.model.entity.Message;
import com.rolling.service.RecipientService;
import com.rolling.service.MessageService;
import com.rolling.service.ReactionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recipients")
@RequiredArgsConstructor
@Tag(name = "Recipient", description = "메시지를 수신할 주체(recipient)정보에 대한 API")
public class RecipientController {

        private final RecipientService recipientService;
        private final MessageService messageService;
        private final ReactionService reactionService;

        @Operation(summary = "Recipient 목록 조회",
                        description = "생성된 모든 Recipient들을 페이지네이션하여 조회합니다. 기본값으로 한 페이지당 8개의 Recipient가 반환됩니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                                        content = @Content(schema = @Schema(
                                                        implementation = PageResponseDto.class))),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @GetMapping("/")
        public ResponseEntity<PageResponseDto<RecipientDto>> getAllRecipients(
                        @Parameter(description = "한 페이지당 반환할 항목 수", example = "8") @RequestParam(
                                        name = "limit", defaultValue = "8") int limit,
                        @Parameter(description = "조회를 시작할 오프셋(페이지 시작점)",
                                        example = "0") @RequestParam(name = "offset",
                                                        defaultValue = "0") int offset) {
                System.out.println("limit: " + limit);
                ServiceResult<PageResponseDto<RecipientDto>> result =
                                recipientService.getAllRecipients(limit, offset);

                if (!result.isSuccess()) {
                        return ResponseEntity.status(result.getStatus())
                                        .body(result.getDataOrNull());
                }

                return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        @Operation(summary = "새 Recipient 생성",
                        description = "새로운 Recipient를 생성합니다. 이름, 배경색, 배경이미지 URL을 지정할 수 있습니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                                        content = @Content(schema = @Schema(
                                                        implementation = RecipientDto.class))),
                        @ApiResponse(responseCode = "400", description = "잘못된 요청",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @PostMapping("/")
        public ResponseEntity<RecipientDto> createRecipient(@Parameter(
                        description = "생성할 Recipient 정보", required = true, schema = @Schema(
                                        implementation = RecipientCreateDto.class)) @RequestBody RecipientCreateDto recipient) {
                System.out.println("recipient: " + recipient);
                ServiceResult<RecipientDto> result = recipientService.createRecipient(recipient);

                return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        @Operation(summary = "ID로 Recipient 조회", description = "특정 ID의 Recipient 정보를 조회합니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                                        content = @Content(schema = @Schema(
                                                        implementation = RecipientDto.class))),
                        @ApiResponse(responseCode = "404", description = "Recipient를 찾을 수 없음",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @GetMapping("/{id}/")
        public ResponseEntity<RecipientDto> getRecipientById(
                        @Parameter(description = "조회할 Recipient의, ID", required = true,
                                        example = "1") @PathVariable("id") Long id) {
                ServiceResult<RecipientDto> result = recipientService.getRecipientById(id);

                if (!result.isSuccess()) {
                        return ResponseEntity.status(result.getStatus())
                                        .body(result.getDataOrNull());
                }

                return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        @Operation(summary = "Recipient 삭제",
                        description = "특정 ID의 Recipient를 삭제합니다. 관련된 메시지와 반응도 함께 삭제됩니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨",
                                        content = @Content),
                        @ApiResponse(responseCode = "404", description = "Recipient를 찾을 수 없음",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @DeleteMapping("/{id}/")
        public ResponseEntity<Void> deleteRecipient(@Parameter(description = "삭제할 Recipient의 ID",
                        required = true, example = "1") @PathVariable Long id) {
                recipientService.deleteRecipient(id);
                return ResponseEntity.noContent().build();
        }

        @Operation(summary = "메시지 생성", description = "특정 Recipient에게 보낼 메시지를 생성합니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                                        content = @Content(schema = @Schema(
                                                        implementation = MessageDto.class))),
                        @ApiResponse(responseCode = "400", description = "잘못된 요청",
                                        content = @Content),
                        @ApiResponse(responseCode = "404", description = "Recipient를 찾을 수 없음",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @PostMapping("/{id}/messages/")
        public ResponseEntity<MessageDto> createMessage(
                        @Parameter(description = "메시지를 받을 Recipient의 ID", required = true,
                                        example = "1") @PathVariable("id") Long id,
                        @Parameter(description = "생성할 메시지 정보",
                                        required = true) @RequestBody Message message) {

                ServiceResult<MessageDto> result = messageService.createMessage(message, id);

                return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        @Operation(summary = "Recipient의 메시지 목록 조회",
                        description = "특정 Recipient에게 보낸 메시지 목록을 페이지네이션하여 조회합니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                                        content = @Content(schema = @Schema(
                                                        implementation = PageResponseDto.class))),
                        @ApiResponse(responseCode = "404", description = "Recipient를 찾을 수 없음",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @GetMapping("/{id}/messages/")
        public ResponseEntity<PageResponseDto<MessageDto>> getMessages(
                        @Parameter(description = "메시지를 조회할 Recipient의 ID", required = true,
                                        example = "1") @PathVariable("id") Long id,
                        @Parameter(description = "한 페이지당 반환할 항목 수", example = "8") @RequestParam(
                                        name = "limit", defaultValue = "8") int limit,
                        @Parameter(description = "조회를 시작할 오프셋(페이지 시작점)",
                                        example = "0") @RequestParam(name = "offset",
                                                        defaultValue = "0") int offset) {
                ServiceResult<PageResponseDto<MessageDto>> result =
                                messageService.getMessagesByRecipientId(id, limit, offset);

                if (!result.isSuccess()) {
                        return ResponseEntity.status(result.getStatus())
                                        .body(result.getDataOrNull());
                }

                return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        @Operation(summary = "메시지 삭제", description = "특정 메시지를 삭제합니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨",
                                        content = @Content),
                        @ApiResponse(responseCode = "404", description = "Recipient를 찾을 수 없음",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @DeleteMapping("/messages/{messageId}/")
        public ResponseEntity<Void> deleteMessage(@Parameter(description = "삭제할 메시지의 ID",
                        required = true, example = "1") @PathVariable("messageId") Long messageId) {
                messageService.deleteMessage(messageId);
                return ResponseEntity.noContent().build();
        }

        @Operation(summary = "Recipient의 반응 목록 조회",
                        description = "특정 Recipient에 대한 반응(이모지) 목록을 페이지네이션하여 조회합니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                                        content = @Content(schema = @Schema(
                                                        implementation = PageResponseDto.class))),
                        @ApiResponse(responseCode = "404", description = "Recipient를 찾을 수 없음",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @GetMapping("/{id}/reactions/")
        public ResponseEntity<PageResponseDto<ReactionDto>> getReactionsByRecipientId(
                        @Parameter(description = "반응을 조회할 Recipient의 ID", required = true,
                                        example = "1") @PathVariable("id") Long id,
                        @Parameter(description = "한 페이지당 반환할 항목 수", example = "8") @RequestParam(
                                        name = "limit", defaultValue = "8") int limit,
                        @Parameter(description = "조회를 시작할 오프셋(페이지 시작점)",
                                        example = "0") @RequestParam(name = "offset",
                                                        defaultValue = "0") int offset) {
                ServiceResult<PageResponseDto<ReactionDto>> result =
                                reactionService.getReactionsByRecipientId(id, limit, offset);

                return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }

        @Operation(summary = "반응 추가",
                        description = "특정 Recipient에 반응(이모지)을 추가하거나 기존 반응 카운트를 증가/감소시킵니다.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                                        content = @Content(schema = @Schema(
                                                        implementation = ReactionDto.class))),
                        @ApiResponse(responseCode = "400", description = "잘못된 요청",
                                        content = @Content),
                        @ApiResponse(responseCode = "404", description = "Recipient를 찾을 수 없음",
                                        content = @Content),
                        @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                                        content = @Content)})
        @PostMapping("/{id}/reactions/")
        public ResponseEntity<ReactionDto> createReaction(
                        @Parameter(description = "반응을 추가할 Recipient의 ID", required = true,
                                        example = "1") @PathVariable("id") Long id,
                        @Parameter(description = "추가할 반응 정보 (이모지와 증가/감소 타입)", required = true,
                                        schema = @Schema(
                                                        implementation = ReactionCreateDto.class)) @RequestBody ReactionCreateDto reactionCreateDto) {
                ServiceResult<ReactionDto> result =
                                reactionService.addReaction(id, reactionCreateDto);

                if (!result.isSuccess()) {
                        return ResponseEntity.status(result.getStatus()).body(null);
                }

                return ResponseEntity.status(result.getStatus()).body(result.getDataOrNull());
        }
}
