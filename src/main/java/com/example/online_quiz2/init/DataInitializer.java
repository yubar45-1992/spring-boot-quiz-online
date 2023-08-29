package com.example.online_quiz2.init;

import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.enumaration.Gender;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.enumaration.Status;
import com.example.online_quiz2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLIntegrityConstraintViolationException;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws SQLIntegrityConstraintViolationException {
        initUsers();
    }

    private void initUsers() throws SQLIntegrityConstraintViolationException {
        if (userService.count() == 0) {
            UserDto userDto = new UserDto();
            userDto.setUsername("mohammad");
            userDto.setPassword(passwordEncoder.encode("123123"));
            userDto.setRole(Role.ADMIN);
            userDto.setFirstName("mohammad");
            userDto.setLastName("aliani");
            userDto.setGender(Gender.MALE);
            userDto.setStatus(Status.ACTIVE);
            userDto.setVersion(0);

            userService.save(userDto);


        }
    }


}
