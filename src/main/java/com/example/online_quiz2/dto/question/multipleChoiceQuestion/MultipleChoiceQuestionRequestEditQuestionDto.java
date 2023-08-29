package com.example.online_quiz2.dto.question.multipleChoiceQuestion;

import com.example.online_quiz2.domain.MultipleChoiceQuestionAnswerSelection;
import com.example.online_quiz2.dto.question.QuestionResponseDto;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor

public class MultipleChoiceQuestionRequestEditQuestionDto extends QuestionResponseDto implements Serializable {


    private Set<MultipleChoiceQuestionAnswerSelectionRequestDto> selections;


}
