package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface DescriptiveQuestionMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<DescriptiveQuestionResponseDto, DescriptiveQuestionDto> {
}
