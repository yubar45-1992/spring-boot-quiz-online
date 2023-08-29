package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends BaseEntityRepository<Course, Long> {

    List<Course> findByProfessorId(Long id);


    @Query("select c from Course c join fetch c.students s where s.id = :studentId")
    List<Course> findAllByStudentId(Long studentId, Pageable pageable);

}
