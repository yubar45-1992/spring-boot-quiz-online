package com.example.online_quiz2.service;


import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.dto.answer_question.AnswerQuestionDto;
import com.example.online_quiz2.dto.answer_question.AnswerQuestionRequestDto;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface AnswerQuestionService extends BaseDTOService<AnswerQuestionDto, Long> {

    Boolean answerToQuestion(AnswerQuestionRequestDto answerQuestionRequestDto);

    Boolean correctTheMultipleChoiceQuestions(Long studentId, Long answeredExamId) throws SQLIntegrityConstraintViolationException;

    List<AnswerQuestionDto> getDescriptiveQuestionsAnswered(Long studentId, Long examId);

    Boolean CorrectTheDescriptiveAnswerQuestion(Long answerQuestionId, Float score) throws SQLIntegrityConstraintViolationException;
}

