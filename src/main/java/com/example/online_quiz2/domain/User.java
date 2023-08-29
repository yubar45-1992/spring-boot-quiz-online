package com.example.online_quiz2.domain;

import com.example.online_quiz2.base.domain.BaseEntity;
import com.example.online_quiz2.enumaration.Gender;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(name = "user_uk_username", columnNames = {"username"})})
public class User extends BaseEntity<Long> {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}

