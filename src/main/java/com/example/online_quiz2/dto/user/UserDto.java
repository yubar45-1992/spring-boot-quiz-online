package com.example.online_quiz2.dto.user;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.enumaration.Gender;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
public class UserDto extends BaseDto<Long> {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Role role;
}

