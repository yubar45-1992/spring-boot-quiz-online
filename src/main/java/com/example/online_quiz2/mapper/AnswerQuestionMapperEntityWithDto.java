package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.AnswerQuestion;
import com.example.online_quiz2.dto.answer_question.AnswerQuestionDto;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerQuestionMapperEntityWithDto extends BaseMapperEntityWithDto<AnswerQuestion, AnswerQuestionDto, Long> {
}
