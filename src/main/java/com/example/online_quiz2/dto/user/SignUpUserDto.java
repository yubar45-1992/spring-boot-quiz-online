package com.example.online_quiz2.dto.user;

import com.example.online_quiz2.enumaration.Gender;
import com.example.online_quiz2.enumaration.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SignUpUserDto implements Serializable {
    @NotBlank(message = "username can not blank")
    private String username;
    @NotBlank(message = "password can not blank")
    private String password;
    @NotBlank(message = "firstName can not blank")
    private String firstName;
    @NotBlank(message = "lastname can not blank")
    private String lastName;
    @NotNull(message = "gender can not blank")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "role can not blank")
    @Enumerated(EnumType.STRING)
    private Role role;

}
