package com.example.online_quiz2.dto.answer_question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AnswerQuestionRequestDto {
    @NotBlank(message = "answeredExam id must not blank")
    private Long answeredExamId;
    @NotBlank(message = "question id must not blank")
    private Long scoredQuestionId;
    @NotBlank(message = "answer id must not blank")
    private String answer;
}
