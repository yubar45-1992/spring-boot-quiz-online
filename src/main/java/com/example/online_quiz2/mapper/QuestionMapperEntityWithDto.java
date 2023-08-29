package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.Question;
import com.example.online_quiz2.dto.question.QuestionDto;
import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapperEntityWithDto extends BaseMapperEntityWithDto<Question, QuestionDto, Long> {


}
