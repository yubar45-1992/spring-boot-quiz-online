package com.example.online_quiz2.dto.scored.question;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.question.QuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ScoredQuestionDto extends BaseDto<Long> {
    @NotNull(message = "score can not be null")
    private Float score;
    private QuestionDto question;
}
