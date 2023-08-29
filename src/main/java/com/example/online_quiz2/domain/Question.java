package com.example.online_quiz2.domain;

import com.example.online_quiz2.base.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Question extends BaseEntity<Long>
        implements Serializable {

    protected String questionText;

    @Column(nullable = false, length = 40)
    protected String title;
    @ManyToOne
    protected User teacher;

    protected String classType = getClass().getSimpleName();

    public Question(Integer version, String questionText, String title, User teacher) {
        super(version);
        this.questionText = questionText;
        this.title = title;
        this.teacher = teacher;
    }
}
