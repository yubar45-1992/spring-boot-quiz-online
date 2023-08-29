package com.example.online_quiz2.dto.user;

import com.example.online_quiz2.enumaration.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherResponseDto {
    private String firstName;
    private String lastName;
    private Gender gender;
}
