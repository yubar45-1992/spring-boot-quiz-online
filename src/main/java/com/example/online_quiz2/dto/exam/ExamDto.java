package com.example.online_quiz2.dto.exam;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.ScoredQuestion;
import com.example.online_quiz2.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedHashSet;

@Getter
@Setter
@NoArgsConstructor
public class ExamDto extends BaseDto<Long> {

    private User teacher;

    private Course course;

    private Collection<ScoredQuestion> scoredQuestions = new LinkedHashSet<>();

    private Long minutesTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
    @NotNull
    private Float score;

}
