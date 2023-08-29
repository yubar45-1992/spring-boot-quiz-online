package com.example.online_quiz2.domain;

import com.example.online_quiz2.base.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "AnsweredQuestionFieldsScoredQuestionAndStudentUk", columnNames = {"scored_question_id", "student_id"})})

public class AnswerQuestion extends BaseEntity<Long> {
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private ScoredQuestion scoredQuestion;
    @ManyToOne
    private AnsweredExam answeredExam;
    @ManyToOne
    private User Student;

    private String answer;

    private Float takenScore;
}
