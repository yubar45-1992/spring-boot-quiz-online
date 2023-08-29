package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionRequestEditQuestionDto;
import org.mapstruct.Mapper;

@Mapper
public interface MultiChoiceQuestionMapperRequestChangeDtoWithDto extends
        BaseMapperDtoWithDto<MultipleChoiceQuestionRequestEditQuestionDto, MultipleChoiceQuestionDto> {

}
