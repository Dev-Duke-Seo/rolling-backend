package com.rolling.service;

import com.rolling.model.dto.PageResponseDto;
import com.rolling.model.dto.ReactionCreateDto;
import com.rolling.model.dto.ReactionDto;

public interface ReactionService {
    ReactionDto addReaction(Long recipientId, ReactionCreateDto reactionCreateDto);

    PageResponseDto<ReactionDto> getReactionsByRecipientId(Long recipientId, int limit, int offset);
}
