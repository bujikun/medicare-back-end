package com.bujikun.fsd.capstone.eHealthcare.exceptions.product;

import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;
import lombok.*;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
