package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.DescriptiveQuestion;
import com.example.online_quiz2.dto.question.DescriptiveQuestionDto;
import org.mapstruct.Mapper;

@Mapper
public interface DescriptiveQuestionMapperEntityWithDto extends BaseMapperEntityWithDto<DescriptiveQuestion, DescriptiveQuestionDto, Long> {
}
