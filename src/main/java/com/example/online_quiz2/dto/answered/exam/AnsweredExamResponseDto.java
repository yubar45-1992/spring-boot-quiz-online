package com.example.online_quiz2.dto.answered.exam;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.exam.ExamResponseStudentWithQuestionsDto;
import com.example.online_quiz2.dto.user.UserResponseDto;
import com.example.online_quiz2.enumaration.ExamStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class AnsweredExamResponseDto extends BaseDto<Long> {
    private ExamResponseStudentWithQuestionsDto exam;
    private UserResponseDto student;
    private ExamStatus examStatus;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time; //زمان و تاریخ باقی مانده آزمون
}
