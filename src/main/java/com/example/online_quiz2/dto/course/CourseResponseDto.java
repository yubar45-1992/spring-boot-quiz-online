package com.example.online_quiz2.dto.course;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponseDto extends BaseDto<Long> {
    private UserResponseDto professor;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<UserResponseDto> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseResponseDto)) return false;
        CourseResponseDto that = (CourseResponseDto) o;
        return Objects.equals(getProfessor(), that.getProfessor()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getStudents(), that.getStudents()
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProfessor(), getTitle(), getStartDate(), getEndDate(), getStudents());
    }
}
