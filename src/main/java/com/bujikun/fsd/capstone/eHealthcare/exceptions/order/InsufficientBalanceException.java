package com.bujikun.fsd.capstone.eHealthcare.exceptions.order;

import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;

public class InsufficientBalanceException extends BaseException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
