package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.Exam;
import com.example.online_quiz2.dto.exam.ExamDto;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapperEntityWithDto extends BaseMapperEntityWithDto<Exam, ExamDto, Long> {
}
