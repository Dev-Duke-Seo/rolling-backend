package com.rolling.service;

import com.rolling.dto.ReactionCreateDto;
import com.rolling.dto.ReactionDto;
import com.rolling.dto.PageResponseDto;

public interface ReactionService {
    ReactionDto addReaction(Long recipientId, ReactionCreateDto reactionCreateDto);

    PageResponseDto<ReactionDto> getReactionsByRecipientId(Long recipientId, int limit, int offset);
}