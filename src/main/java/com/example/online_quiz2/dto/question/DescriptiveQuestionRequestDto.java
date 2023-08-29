package com.example.online_quiz2.dto.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class DescriptiveQuestionRequestDto extends QuestionRequestDto {
    @NotNull(message = "score can not be null")
    private Float score;
    private Long examId;
}
