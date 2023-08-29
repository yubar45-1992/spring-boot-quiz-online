package com.example.online_quiz2.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class SaveCourseDto {
    @NotBlank(message = "title course is null")
    private String title;
    @NotNull(message = "start date course is null")
    private LocalDate startDate;
    @NotNull(message = "end date course is null")
    private LocalDate endDate;
}
