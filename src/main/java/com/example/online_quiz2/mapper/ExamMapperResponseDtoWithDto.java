package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.exam.ExamResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<ExamResponseDto, ExamDto> {
}
