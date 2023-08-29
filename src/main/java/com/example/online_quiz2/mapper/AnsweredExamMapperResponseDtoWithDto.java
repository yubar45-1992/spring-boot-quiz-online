package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamDto;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface AnsweredExamMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<AnsweredExamResponseDto, AnsweredExamDto> {
}
