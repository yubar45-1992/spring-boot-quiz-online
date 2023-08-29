package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface MultiChoiceQuestionMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<MultipleChoiceQuestionResponseDto, MultipleChoiceQuestionDto> {
}
