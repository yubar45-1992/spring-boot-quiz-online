package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface DescriptiveQuestionMapperRequestDtoWithDto extends
        BaseMapperDtoWithDto<DescriptiveQuestionRequestDto,
                DescriptiveQuestionDto> {
}
