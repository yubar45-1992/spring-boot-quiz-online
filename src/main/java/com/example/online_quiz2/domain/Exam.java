package com.example.online_quiz2.domain;

import com.example.online_quiz2.base.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.LinkedHashSet;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Exam extends BaseEntity<Long> implements Serializable {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private User teacher;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Course course;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Collection<ScoredQuestion> scoredQuestions = new LinkedHashSet<>();

    private Long minutesTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private Float score;

}
