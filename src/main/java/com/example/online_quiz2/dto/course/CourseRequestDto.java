package com.example.online_quiz2.dto.course;

import com.example.online_quiz2.base.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDto extends BaseDto<Long> {

    @NotBlank
    private String title;
    @NotBlank
    private LocalDate startDate;
    @NotBlank
    private LocalDate endDate;

}
