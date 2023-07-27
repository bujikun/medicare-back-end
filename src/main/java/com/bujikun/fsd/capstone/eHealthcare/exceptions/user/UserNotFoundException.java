package com.bujikun.fsd.capstone.eHealthcare.exceptions.user;

import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
