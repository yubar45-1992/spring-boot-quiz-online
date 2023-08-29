package com.example.online_quiz2.dto.question.multichoicequestionanwer;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class MultipleChoiceQuestionAnswerSelectionRequestDto implements Serializable {
    private String answer;
    private Boolean answerCheck;
}
