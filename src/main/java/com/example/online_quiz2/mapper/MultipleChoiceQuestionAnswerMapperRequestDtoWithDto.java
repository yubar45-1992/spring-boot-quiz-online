package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionDto;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface MultipleChoiceQuestionAnswerMapperRequestDtoWithDto extends
        BaseMapperDtoWithDto<MultipleChoiceQuestionAnswerSelectionRequestDto,
                MultipleChoiceQuestionAnswerSelectionDto> {
}
