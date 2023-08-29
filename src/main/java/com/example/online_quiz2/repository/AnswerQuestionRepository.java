package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.AnswerQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerQuestionRepository extends BaseEntityRepository<AnswerQuestion, Long> {

    @Query("select a from AnswerQuestion   a   where  a.answeredExam.id= :answeredExamId and a.Student.id = :studentId")
    List<AnswerQuestion> findByAnsweredExamIdAndStudentId(Long answeredExamId, Long studentId);
}
