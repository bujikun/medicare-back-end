package com.bujikun.fsd.capstone.eHealthcare.exceptions;

public class BaseException extends RuntimeException{
    protected  String message;

    public BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
