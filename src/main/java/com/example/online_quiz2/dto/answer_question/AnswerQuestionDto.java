package com.example.online_quiz2.dto.answer_question;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import com.example.online_quiz2.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerQuestionDto extends BaseDto<Long> {

    private ScoredQuestionDto scoredQuestion;

    private AnsweredExamDto answeredExam;

    private UserDto Student;

    private String answer;

    private Float takenScore;

}
