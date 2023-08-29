package com.example.online_quiz2.service;


import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.dto.question.DescriptiveQuestionDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionRequestDto;

import java.sql.SQLIntegrityConstraintViolationException;


public interface DescriptiveQuestionService extends BaseDTOService<DescriptiveQuestionDto, Long> {

    @Override
    DescriptiveQuestionDto save(DescriptiveQuestionDto e)
            throws SQLIntegrityConstraintViolationException;

    Boolean requestSave(DescriptiveQuestionRequestDto descriptiveQuestionRequestDto) throws SQLIntegrityConstraintViolationException;
}



