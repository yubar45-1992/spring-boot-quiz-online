package com.example.online_quiz2.controller;

import com.example.online_quiz2.dto.answer_question.AnswerQuestionRequestDto;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamResponseDto;
import com.example.online_quiz2.dto.course.CourseStudentResponseDto;
import com.example.online_quiz2.dto.exam.ExamResponseWithOutQuestionDto;
import com.example.online_quiz2.service.AnswerQuestionService;
import com.example.online_quiz2.service.AnsweredExamService;
import com.example.online_quiz2.service.CourseService;
import com.example.online_quiz2.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final CourseService courseService;

    private final ExamService examService;

    private final AnsweredExamService answeredExamService;

    private final AnswerQuestionService answerQuestionService;

    @PreAuthorize(value = "hasRole('STUDENT')")
    @GetMapping("/get-student-courses")
    public ResponseEntity<List<CourseStudentResponseDto>> getStudentCourses(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam String sortBy) {
        return ResponseEntity.ok(
                courseService.responseFindAllWithStudentId(pageNumber, pageSize, sortBy));
    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @GetMapping("/get-exams-course")
    public ResponseEntity<List<ExamResponseWithOutQuestionDto>> getExamsCourse(@RequestParam Long courseId,
                                                                               @RequestParam Integer pageNumber,
                                                                               @RequestParam Integer pageSize,
                                                                               @RequestParam String sortBy) {
        return ResponseEntity.ok(
                examService.findAllByCourseId(courseId, pageNumber, pageSize, sortBy));
    }

    @PreAuthorize(value = "hasRole('STUDENT')")
    @GetMapping("/view-exam-for-answer")
    public ResponseEntity<AnsweredExamResponseDto> answerToExam(@RequestParam Long examId) throws Exception {
        return ResponseEntity.ok(
                answeredExamService.createAnswerExam(examId));
    }


    @PreAuthorize(value = "hasRole('STUDENT')")
    @PostMapping("/answer-to-question")
    public ResponseEntity<Boolean> answerToQuestion(@RequestBody AnswerQuestionRequestDto answerQuestionRequestDto) {
        return new ResponseEntity<>(answerQuestionService.answerToQuestion(answerQuestionRequestDto), HttpStatus.CREATED);

    }


}
