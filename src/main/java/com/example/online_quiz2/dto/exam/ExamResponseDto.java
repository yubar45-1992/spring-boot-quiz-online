package com.example.online_quiz2.dto.exam;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.course.CourseResponseDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionResponseDto;
import com.example.online_quiz2.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class ExamResponseDto extends BaseDto<Long> {
    private UserResponseDto teacher;
    private CourseResponseDto course;
    private Collection<ScoredQuestionResponseDto> scoredQuestions;
    private Long minutesTime;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Float score;
}
