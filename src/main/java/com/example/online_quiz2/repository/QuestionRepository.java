package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends BaseEntityRepository<Question, Long> {

    List<Question> findAllByTeacherId(Long teacherId);


}
