package com.example.online_quiz2.dto.exam;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.course.CourseExamResponseDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionResponseDto;
import com.example.online_quiz2.dto.user.TeacherResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedHashSet;

@Getter
@Setter
@NoArgsConstructor
public class ExamResponseStudentWithQuestionsDto extends BaseDto<Long> {
    private TeacherResponseDto teacher;
    private CourseExamResponseDto course;
    private Long minutesTime;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Collection<ScoredQuestionResponseDto> scoredQuestions = new LinkedHashSet<>();
}
