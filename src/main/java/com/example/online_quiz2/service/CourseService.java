package com.example.online_quiz2.service;

import com.example.online_quiz2.base.service.BaseDTOService;
import com.example.online_quiz2.dto.course.CourseDto;
import com.example.online_quiz2.dto.course.CourseResponseDto;
import com.example.online_quiz2.dto.course.CourseStudentResponseDto;
import com.example.online_quiz2.dto.course.SaveCourseDto;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

;

public interface CourseService extends BaseDTOService<CourseDto, Long> {
//    CourseResponseDto saveCourse(CourseRequestDto courseRequestDto) throws SQLIntegrityConstraintViolationException;

    Boolean addStudentToCourse(Long userId, Long courseId) throws SQLIntegrityConstraintViolationException;

    Boolean addTeacherToCourse(Long userId, Long courseId);

    List<CourseResponseDto> getTeacherCourses();

    CourseResponseDto responseFindById(Long courseId);

    List<CourseResponseDto> responseFindAll();

    List<CourseStudentResponseDto> responseFindAllWithStudentId(Integer pageNumber, Integer pageSize, String sortBy);

    Boolean responseSave(SaveCourseDto saveCourseDto) throws SQLIntegrityConstraintViolationException;


// Course save(Course course, String dateEndCourse, String dateStartCourse) throws ParseException;
//
// Course saveProfessor(Optional<User> user, Optional<Course> course);
//
// String spiltCourseId(String id);
//
// void saveStudent(Optional<Course> course, String student);
//
// void deleteStudent(Optional<Course> course, String student);
//
}
