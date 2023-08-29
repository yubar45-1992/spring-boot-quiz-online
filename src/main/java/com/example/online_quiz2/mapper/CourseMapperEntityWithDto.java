package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.dto.course.CourseDto;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapperEntityWithDto extends BaseMapperEntityWithDto<Course, CourseDto, Long> {
}
