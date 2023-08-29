package com.example.online_quiz2.domain;

import com.example.online_quiz2.base.domain.BaseEntity;
import com.example.online_quiz2.enumaration.ExamStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name = "AnsweredExamFieldsExamAndStudentUk", columnNames = {"exam_id", "student_id"})})
@Entity
// کل اطلاعات پاسخنامه
public class AnsweredExam extends BaseEntity<Long> {

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private User student;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time; //زمان و تاریخ باقی مانده آزمون

    private ExamStatus examStatus;

}
