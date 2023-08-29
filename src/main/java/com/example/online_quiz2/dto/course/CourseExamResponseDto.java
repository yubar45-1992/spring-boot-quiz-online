package com.example.online_quiz2.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CourseExamResponseDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
}
