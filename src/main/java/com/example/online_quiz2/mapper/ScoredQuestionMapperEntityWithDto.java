package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.ScoredQuestion;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import org.mapstruct.Mapper;

@Mapper
public interface ScoredQuestionMapperEntityWithDto extends
        BaseMapperEntityWithDto<ScoredQuestion,
                ScoredQuestionDto, Long> {
}
