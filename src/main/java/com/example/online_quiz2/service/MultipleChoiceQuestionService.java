package com.example.online_quiz2.service;


import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionRequestDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionRequestEditQuestionDto;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface MultipleChoiceQuestionService extends BaseDTOService<MultipleChoiceQuestionDto, Long> {


    MultipleChoiceQuestionDto editMultipleChoiceQuestion(@Valid MultipleChoiceQuestionDto multipleChoiceQuestionDto)
            throws SQLIntegrityConstraintViolationException;

    Boolean requestSave(MultipleChoiceQuestionRequestDto multipleChoiceQuestionRequestDto) throws SQLIntegrityConstraintViolationException;

    Boolean editMultipleChoiceQuestion(MultipleChoiceQuestionRequestEditQuestionDto multipleChoiceQuestionRequestEditQuestionDto) throws SQLIntegrityConstraintViolationException, AccessDeniedException;


}
