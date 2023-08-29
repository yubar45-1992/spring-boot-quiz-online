package com.example.online_quiz2.base.exception;


public class BadRequestRuntimeException extends RuntimeException {


    private static final long serialVersionUID = 8390381744331553213L;

    public BadRequestRuntimeException(String message) {
        super(message);
    }
}
