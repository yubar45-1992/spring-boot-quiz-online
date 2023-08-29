package com.example.online_quiz2.dto.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DescriptiveQuestionResponseDto extends QuestionResponseDto {
    private String answer;

    private Float score;

    private Long examId;
}
