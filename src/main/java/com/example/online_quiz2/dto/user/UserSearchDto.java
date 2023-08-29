package com.example.online_quiz2.dto.user;

import com.example.online_quiz2.enumaration.Gender;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserSearchDto implements Serializable {
    Integer pageNumber;
    Integer pageSize;
    String sortBy;
    private String firstName;
    private String lastName;
    private String username;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Role role;
}
