package com.example.online_quiz2.dto.question.multipleChoiceQuestion;

import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.question.QuestionDto;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MultipleChoiceQuestionDto extends QuestionDto {
    private Set<MultipleChoiceQuestionAnswerSelectionDto> selections = new HashSet<>();


    // for only data entry
   // @NotNull(message = "score can not be null")
    private Float score;
    private ExamDto exam;
}
