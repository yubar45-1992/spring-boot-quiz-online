package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.MultipleChoiceQuestion;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionDto;
import org.mapstruct.Mapper;

@Mapper
public interface MultipleChoiceQuestionMapperEntityWithDto extends BaseMapperEntityWithDto<MultipleChoiceQuestion, MultipleChoiceQuestionDto, Long> {
}
