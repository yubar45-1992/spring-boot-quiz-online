package com.example.online_quiz2.dto.question;

import com.example.online_quiz2.dto.exam.ExamDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DescriptiveQuestionDto extends QuestionDto {
    private String answer;
    @NotNull(message = "score can not be null")
    private Float score;
    private ExamDto examDto;
}
