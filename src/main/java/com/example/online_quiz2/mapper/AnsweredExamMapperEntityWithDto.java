package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.AnsweredExam;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamDto;
import org.mapstruct.Mapper;

@Mapper
public interface AnsweredExamMapperEntityWithDto extends BaseMapperEntityWithDto<AnsweredExam, AnsweredExamDto, Long> {
}
