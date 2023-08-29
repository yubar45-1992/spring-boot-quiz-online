package com.example.online_quiz2.controller;

//import com.example.online_quiz2.domain.Exam;

import com.example.online_quiz2.dto.answer_question.AnswerQuestionDto;
import com.example.online_quiz2.dto.course.CourseResponseDto;
import com.example.online_quiz2.dto.exam.CreateExamDto;
import com.example.online_quiz2.dto.exam.ExamResponseDto;
import com.example.online_quiz2.dto.exam.ExamResponseWithOutQuestionDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionRequestDto;
import com.example.online_quiz2.dto.question.QuestionResponseDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionRequestDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionRequestEditQuestionDto;
import com.example.online_quiz2.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;

    @Autowired
    AnswerQuestionService answerQuestionService;

    @Autowired
    private DescriptiveQuestionService descriptiveQuestionService;

    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;

    @PreAuthorize(value = "hasRole('TEACHER')")
    @GetMapping("/all-teacher-courses")
    public ResponseEntity<List<CourseResponseDto>> viewTeacherCourses() {

        return ResponseEntity.ok().body(courseService.getTeacherCourses());
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @GetMapping("/view-CoursesExams")
    public ResponseEntity<List<ExamResponseWithOutQuestionDto>> selectCourse(@NotNull @RequestParam Long courseId, @RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam String sortBy) throws ChangeSetPersister.NotFoundException {


        return ResponseEntity.ok(examService.findAllByCourseId(courseId, pageNumber, pageSize, sortBy));
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @PostMapping("/new-exam")
    public ResponseEntity addNewExam(@RequestBody
                                     CreateExamDto examDto
    )
            throws SQLIntegrityConstraintViolationException {
        examService.customSave(examDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @PostMapping("/new-descriptive-question")
    public ResponseEntity
    newDescriptiveQuestion(@RequestBody DescriptiveQuestionRequestDto descriptiveQuestionRequestDto) throws SQLIntegrityConstraintViolationException {
        descriptiveQuestionService.requestSave(descriptiveQuestionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PreAuthorize(value = "hasRole('TEACHER')")
    @PostMapping("/new-multiple-choice-question")
    public ResponseEntity<Boolean>
    newMultipleChoiceQuestion(@RequestBody MultipleChoiceQuestionRequestDto multipleChoiceQuestionDto)
            throws SQLIntegrityConstraintViolationException {
        multipleChoiceQuestionService.requestSave(multipleChoiceQuestionDto);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).build();
    }


    @PreAuthorize(value = "hasRole('TEACHER')")
    @GetMapping("/list-of-exam")
    public ResponseEntity<List<ExamResponseDto>>
    getListOfExam(@NotNull @RequestParam Long courseId,
                  @NotNull @RequestParam Integer pageNumber,
                  @NotNull @RequestParam Integer pageSize,
                  @NotNull @RequestParam String sortBy) {
        return ResponseEntity.ok(
                examService.findAllByTeacherIdAndCourseId(courseId, pageNumber, pageSize, sortBy)
        );
    }


    @PreAuthorize(value = "hasRole('TEACHER')")
    @GetMapping("/show-question")
    public ResponseEntity<List<QuestionResponseDto>>
    showQuestion(@NotNull @RequestParam Long courseId
    ) {
        return ResponseEntity.ok(
                questionService.findByCourse(courseId)
        );
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @PutMapping("/add-question-from-question-bank")
    public ResponseEntity<Boolean>
    addQuestionFromTeacherBank(@RequestParam Long questionId,
                               @RequestParam Long examId, @NotNull @RequestParam Float questionScore) throws AccessDeniedException, SQLIntegrityConstraintViolationException {
        return ResponseEntity.ok(examService.addQuestionFromTeacherQuestionBank(questionId, examId, questionScore));
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @PutMapping("/edit-multiple-choice-question")
    public ResponseEntity<Boolean> editMultipleChoiceQuestion(@RequestBody MultipleChoiceQuestionRequestEditQuestionDto
                                                                      multipleChoiceQuestionRequestDto) throws SQLIntegrityConstraintViolationException, AccessDeniedException {
        multipleChoiceQuestionService
                .editMultipleChoiceQuestion
                        (multipleChoiceQuestionRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @GetMapping("/view-student-answered-descriptive-questions")
    public ResponseEntity<List<AnswerQuestionDto>> getStudentAnswerDescriptiveQuestion(@RequestParam @NotNull Long studentId, @RequestParam @NotNull Long answerdExamId) {

        return ResponseEntity.ok(answerQuestionService.getDescriptiveQuestionsAnswered(studentId, answerdExamId));
    }

    @PreAuthorize(value = "hasRole('TEACHER')")
    @PutMapping("/correct-the-descriptive-question")
    public ResponseEntity<Boolean> correctTheDescriptiveQuestion(@RequestParam @NotNull Long answeredQuestionId, @RequestParam @NotNull Float score) throws SQLIntegrityConstraintViolationException {
        return new ResponseEntity<>(answerQuestionService.CorrectTheDescriptiveAnswerQuestion(answeredQuestionId, score), HttpStatus.NO_CONTENT);
    }


    @PreAuthorize(value = "hasRole('TEACHER')")
    @PutMapping("/correct-the-multiple-choice-question")
    public ResponseEntity<Boolean> correctTheMultipleChoiceQuestionQuestion(@RequestParam @NotNull Long studentId, @RequestParam @NotNull Long answeredExamId) throws SQLIntegrityConstraintViolationException {
        return new ResponseEntity<>(answerQuestionService.correctTheMultipleChoiceQuestions(studentId, answeredExamId), HttpStatus.NO_CONTENT);
    }


}
