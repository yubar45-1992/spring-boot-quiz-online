package com.example.online_quiz2.base.exception;


public class DTONotFoundRuntimeException extends RuntimeException {


    private static final long serialVersionUID = 6010646329460672810L;

    public DTONotFoundRuntimeException(String message) {
        super(message);
    }
}
