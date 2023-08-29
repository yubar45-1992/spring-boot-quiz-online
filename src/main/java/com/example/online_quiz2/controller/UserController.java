package com.example.online_quiz2.controller;

import com.example.online_quiz2.dto.user.SignUpUserDto;
import com.example.online_quiz2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpUserDto signUpUserDto)
            throws SQLIntegrityConstraintViolationException {
        userService.signUpUserDto(signUpUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
