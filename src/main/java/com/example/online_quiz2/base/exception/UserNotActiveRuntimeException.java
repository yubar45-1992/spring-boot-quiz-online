package com.example.online_quiz2.base.exception;


public class UserNotActiveRuntimeException extends RuntimeException {


    private static final long serialVersionUID = 683611145262042544L;

    public UserNotActiveRuntimeException(String message) {
        super(message);
    }
}
