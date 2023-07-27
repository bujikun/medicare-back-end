package com.bujikun.fsd.capstone.eHealthcare.exceptions.user;

import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;

public class UserExistsException extends BaseException {
    public UserExistsException(String message) {
        super(message);
    }
}
