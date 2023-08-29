package com.example.online_quiz2.mapper;

import com.example.online_quiz2.base.mapper.BaseMapperDtoWithDto;
import com.example.online_quiz2.dto.course.CourseDto;
import com.example.online_quiz2.dto.course.CourseResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapperResponseDtoWithDto extends
        BaseMapperDtoWithDto<CourseResponseDto, CourseDto> {
}
