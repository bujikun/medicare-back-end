package com.bujikun.fsd.capstone.eHealthcare.exceptions.order;

import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;

public class OrderNotFoundException extends BaseException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
