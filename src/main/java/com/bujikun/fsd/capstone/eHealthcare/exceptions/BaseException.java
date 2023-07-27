package com.bujikun.fsd.capstone.eHealthcare.exceptions;

import lombok.NoArgsConstructor;

public class BaseException extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
