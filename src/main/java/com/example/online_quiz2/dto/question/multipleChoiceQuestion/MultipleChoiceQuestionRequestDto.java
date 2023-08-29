package com.example.online_quiz2.dto.question.multipleChoiceQuestion;

import com.example.online_quiz2.dto.question.QuestionRequestDto;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MultipleChoiceQuestionRequestDto extends QuestionRequestDto {
    private Set<MultipleChoiceQuestionAnswerSelectionRequestDto> selections = new HashSet<>();
    @NotNull(message = "score can not be null")
    private Float score;
    private Long examId;
}
