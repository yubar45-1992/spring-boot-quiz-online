package com.example.online_quiz2.dto.exam;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.course.CourseExamResponseDto;
import com.example.online_quiz2.dto.user.TeacherResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ExamResponseWithOutQuestionDto extends BaseDto<Long> {
    private TeacherResponseDto teacher;
    private CourseExamResponseDto course;
    private Long minutesTime;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Float score;
}
