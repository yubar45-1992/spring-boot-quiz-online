package com.example.online_quiz2.dto.user;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.enumaration.Gender;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto extends BaseDto<Long> {
    @NotBlank(message = "username can not blank")
    private String username;
    @NotBlank(message = "password can not blank")
    private String password;
    @NotBlank(message = "firstName can not blank")
    private String firstName;
    @NotBlank(message = "lastname can not blank")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Role role;
}
