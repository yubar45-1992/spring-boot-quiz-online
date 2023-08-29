package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.Exam;
import com.example.online_quiz2.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends BaseEntityRepository<Exam, Long> {

    List<Exam> findAllByCourseId(Long courseId, Pageable pageable);
    Page<Exam> findExamsByTeacherAndCourse(User user, Course course, Pageable pageable);


}
