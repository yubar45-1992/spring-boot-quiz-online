package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.AnsweredExam;
import org.springframework.stereotype.Repository;

@Repository
public interface AnsweredExamRepository extends BaseEntityRepository<AnsweredExam, Long> {
    AnsweredExam findByExam_Id(Long examId);
}
