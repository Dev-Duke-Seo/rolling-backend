package com.rolling.service;

import com.common.dto.ServiceResult;
import com.common.dto.PageResponseDto;
import com.rolling.model.dto.ReactionCreateDto;
import com.rolling.model.dto.ReactionDto;

public interface ReactionService {
    ServiceResult<ReactionDto> addReaction(Long recipientId, ReactionCreateDto reactionCreateDto);

    ServiceResult<PageResponseDto<ReactionDto>> getReactionsByRecipientId(Long recipientId,
            int limit, int offset);
}
