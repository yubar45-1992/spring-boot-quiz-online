package com.example.online_quiz2.domain;


import com.example.online_quiz2.base.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class MultipleChoiceQuestionAnswerSelection extends BaseEntity<Long> {

    private String answer;

    private Boolean answerCheck;

    public MultipleChoiceQuestionAnswerSelection(Integer version, String answer, Boolean answerCheck) {
        super(version);
        this.answer = answer;
        this.answerCheck = answerCheck;
    }
}
