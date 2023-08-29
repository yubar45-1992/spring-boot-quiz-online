package com.example.online_quiz2.service;


import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamDto;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamResponseDto;


public interface AnsweredExamService extends BaseDTOService<AnsweredExamDto, Long> {

    AnsweredExamResponseDto createAnswerExam(Long examId) throws Exception;
}
