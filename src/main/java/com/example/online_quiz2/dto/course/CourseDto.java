package com.example.online_quiz2.dto.course;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto extends BaseDto<Long> {
    private User professor;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<User> students;
}
