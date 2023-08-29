package com.example.online_quiz2.base.exception;


public class EntityNotFoundRuntimeException extends RuntimeException {


    private static final long serialVersionUID = 683611145262042544L;

    public EntityNotFoundRuntimeException(String message) {
        super(message);
    }
}
