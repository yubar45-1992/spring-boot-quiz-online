package com.example.online_quiz2.util;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;

public abstract class ScoreUtil {

    public static void checkScoreValidation(Float score) {
        if (score <= 0) {
            throw new BadRequestRuntimeException("score can not be zero or less than zero");
        }
    }
}
