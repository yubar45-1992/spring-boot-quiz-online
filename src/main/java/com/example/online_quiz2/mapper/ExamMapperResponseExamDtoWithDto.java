package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.exam.ExamResponseWithOutQuestionDto;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapperResponseExamDtoWithDto extends
        BaseMapperDtoWithDto<ExamResponseWithOutQuestionDto, ExamDto> {
}
