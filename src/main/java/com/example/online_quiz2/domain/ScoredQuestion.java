package com.example.online_quiz2.domain;

import com.example.online_quiz2.base.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
// استاد میگه این سوال دو نمره داره
public class ScoredQuestion extends BaseEntity<Long> {
    private Float score;
    @ManyToOne
    private Question question;


}
