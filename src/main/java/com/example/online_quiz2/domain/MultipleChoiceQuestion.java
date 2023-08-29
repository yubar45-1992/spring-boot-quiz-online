package com.example.online_quiz2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity

public class MultipleChoiceQuestion extends Question implements Serializable {

    @OneToMany
    @JoinColumn(name = "question_id")
    private Set<MultipleChoiceQuestionAnswerSelection> selections;

    public MultipleChoiceQuestion(Integer version, String questionText, String title, User teacher, Course course, Set<MultipleChoiceQuestionAnswerSelection> selections) {
        super(version, questionText, title, teacher);
        this.selections = selections;
    }
}
