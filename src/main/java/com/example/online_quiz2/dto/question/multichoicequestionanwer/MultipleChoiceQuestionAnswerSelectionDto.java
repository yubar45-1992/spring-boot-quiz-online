package com.example.online_quiz2.dto.question.multichoicequestionanwer;


import com.example.online_quiz2.base.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MultipleChoiceQuestionAnswerSelectionDto extends BaseDto<Long> {

    private String answer;
    private Boolean answerCheck;
}
