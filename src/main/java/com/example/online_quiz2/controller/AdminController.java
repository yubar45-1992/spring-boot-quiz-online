package com.example.online_quiz2.controller;


import com.example.online_quiz2.dto.course.CourseResponseDto;
import com.example.online_quiz2.dto.course.SaveCourseDto;
import com.example.online_quiz2.dto.user.UserResponseDto;
import com.example.online_quiz2.dto.user.UserSearchDto;
import com.example.online_quiz2.service.CourseService;
import com.example.online_quiz2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class AdminController {
    private final UserService userservice;
    private final CourseService courseService;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/not-verify-users")
    public ResponseEntity<List<UserResponseDto>>
    getNotVerifiedUsers(@RequestParam Integer pageNumber,
                        @RequestParam Integer pageSize,
                        @RequestParam String sortBy) {
        return ResponseEntity.ok(
                userservice.findAllUserByAdmin(pageNumber, pageSize, sortBy)
        );
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/change-status-to-active")
    public ResponseEntity activateUser(@RequestParam Long userId) {
        userservice.changeUserStatus(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping("/search-admin-in-users")
    public ResponseEntity<List<UserResponseDto>> searchAdminInUsers(@RequestBody
                                                                    UserSearchDto userSearchDTO) {
        return ResponseEntity.ok(
                userservice.AllByAdvanceSearch(userSearchDTO)
        );
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping("/create-course")
    public ResponseEntity<Boolean> saveCourse(@RequestBody SaveCourseDto saveCourseDto) throws SQLIntegrityConstraintViolationException {
        courseService.responseSave(saveCourseDto);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).build();
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/show-all-course")
    public ResponseEntity<List<CourseResponseDto>> showAllCourse() {
        return ResponseEntity.ok(
                courseService.responseFindAll()
        );
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping("/add-student")
    public ResponseEntity addStudentToCourse(@RequestParam Long userId,
                                             @RequestParam Long courseId) throws SQLIntegrityConstraintViolationException {
        courseService.addStudentToCourse(userId, courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping("/add-teacher")
    public ResponseEntity addTeacherToCourse(@RequestParam Long userId,
                                             @RequestParam Long courseId) {
        courseService.addTeacherToCourse(userId, courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
