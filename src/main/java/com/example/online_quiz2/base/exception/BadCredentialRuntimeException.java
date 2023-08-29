package com.example.online_quiz2.base.exception;


public class BadCredentialRuntimeException extends RuntimeException {


    private static final long serialVersionUID = 6501523311277298914L;

    public BadCredentialRuntimeException(String message) {
        super(message);
    }
}
