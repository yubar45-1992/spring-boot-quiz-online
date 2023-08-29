package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.question.QuestionDto;
import com.example.online_quiz2.dto.question.QuestionResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<QuestionResponseDto, QuestionDto> {
}
