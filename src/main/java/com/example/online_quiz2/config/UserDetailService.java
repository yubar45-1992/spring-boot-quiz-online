package com.example.online_quiz2.config;


import com.example.online_quiz2.base.exception.UserNotActiveRuntimeException;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.enumaration.Status;
import com.example.online_quiz2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Qualifier("User")
@Primary
@RequiredArgsConstructor
@Log4j2
public class UserDetailService implements UserDetailsService {
    private final UserService userService;

    private UserDto userRequestDTO;

    public UserDto getUserRequestDTO() {
        if (!userRequestDTO.getStatus().equals(Status.ACTIVE)) {
            throw new UserNotActiveRuntimeException("user not active");
        }
        return userRequestDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, UserNotActiveRuntimeException {
        userRequestDTO = userService.findByUsername(username);
        if (userRequestDTO == null) {
            throw new UsernameNotFoundException("user not found");
        }
        if (!userRequestDTO.getStatus().equals(Status.ACTIVE)) {
            log.error("user not active");
        }
        return User.withUsername(username).password(userRequestDTO.getPassword())
                .authorities("ROLE_".concat(userRequestDTO.getRole().name())
                ).build();
    }
}
