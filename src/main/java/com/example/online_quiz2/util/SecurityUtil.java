package com.example.online_quiz2.util;

import com.example.online_quiz2.config.UserDetailService;

import java.nio.file.AccessDeniedException;

public class SecurityUtil {

    public static void checkCurrentUser(Long userRequestId, UserDetailService userDetailService) throws AccessDeniedException {
        if (userRequestId != userDetailService.getUserRequestDTO().getId()) {
            throw new AccessDeniedException("you dont have access for this operation ");
        }
    }


}
