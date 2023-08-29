package com.example.online_quiz2.service;


import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.dto.question.QuestionDto;
import com.example.online_quiz2.dto.question.QuestionResponseDto;

import java.util.List;


public interface QuestionService extends BaseDTOService<QuestionDto, Long> {

    List<QuestionResponseDto> findByCourse(Long courseId);

}
