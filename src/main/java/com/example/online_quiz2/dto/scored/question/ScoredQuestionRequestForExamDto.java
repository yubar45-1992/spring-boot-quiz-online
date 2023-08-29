package com.example.online_quiz2.dto.scored.question;

import com.example.online_quiz2.dto.question.QuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ScoredQuestionRequestForExamDto implements Serializable {
    private Float score;
    private QuestionDto question;
}
