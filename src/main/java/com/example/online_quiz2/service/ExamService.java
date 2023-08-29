package com.example.online_quiz2.service;


import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.dto.exam.CreateExamDto;
import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.exam.ExamResponseDto;
import com.example.online_quiz2.dto.exam.ExamResponseWithOutQuestionDto;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface ExamService extends BaseDTOService<ExamDto, Long> {

    void customSave(CreateExamDto examDto) throws SQLIntegrityConstraintViolationException;

    List<ExamResponseWithOutQuestionDto> findAllByCourseId(Long courseId, Integer pageNumber, Integer pageSize, String sortBy);

    List<ExamResponseDto> findAllByTeacherIdAndCourseId(Long courseId, Integer pageNumber, Integer pageSize, String sortBy);

    Boolean addQuestionFromTeacherQuestionBank(Long questionId, Long examId, Float questionScore) throws AccessDeniedException, SQLIntegrityConstraintViolationException;
}
