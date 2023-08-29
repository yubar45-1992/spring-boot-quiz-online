package com.example.online_quiz2.dto.answered.exam;

import com.example.online_quiz2.enumaration.ExamStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class AnsweredExamRequestDto implements Serializable {
    private Long examId;
    private ExamStatus examStatus;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time; //زمان و تاریخ باقی مانده آزمون
}
