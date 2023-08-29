package com.example.online_quiz2.dto.scored.question;

import com.example.online_quiz2.dto.question.QuestionResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ScoredQuestionResponseDto implements Serializable {
    private Float score;
    private QuestionResponseDto question;
}
