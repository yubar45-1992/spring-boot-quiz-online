package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface ScoredQuestionMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<ScoredQuestionResponseDto, ScoredQuestionDto> {
}
