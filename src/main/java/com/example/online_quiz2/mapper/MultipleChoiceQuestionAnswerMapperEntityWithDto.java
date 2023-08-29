package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.MultipleChoiceQuestionAnswerSelection;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionDto;
import org.mapstruct.Mapper;

@Mapper
public interface MultipleChoiceQuestionAnswerMapperEntityWithDto extends BaseMapperEntityWithDto<MultipleChoiceQuestionAnswerSelection, MultipleChoiceQuestionAnswerSelectionDto, Long> {
}
