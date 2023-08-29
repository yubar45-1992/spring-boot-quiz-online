package com.example.online_quiz2.dto.course;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.user.TeacherResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class CourseStudentResponseDto extends BaseDto<Long> {
    private TeacherResponseDto professor;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudentResponseDto that = (CourseStudentResponseDto) o;
        return Objects.equals(professor, that.professor) && Objects.equals(title, that.title) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professor, title, startDate, endDate);
    }
}
