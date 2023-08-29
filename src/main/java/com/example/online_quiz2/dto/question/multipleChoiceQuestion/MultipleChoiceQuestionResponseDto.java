package com.example.online_quiz2.dto.question.multipleChoiceQuestion;

import com.example.online_quiz2.domain.MultipleChoiceQuestionAnswerSelection;
import com.example.online_quiz2.dto.question.QuestionResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MultipleChoiceQuestionResponseDto extends QuestionResponseDto {
    private Set<MultipleChoiceQuestionAnswerSelection> selections = new HashSet<>();
    private Float score;
    private Long examId;
}
