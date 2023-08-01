package com.bujikun.fsd.capstone.eHealthcare.exceptions.order;

import com.bujikun.fsd.capstone.eHealthcare.exceptions.user.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderControllerAdvice {
    @ExceptionHandler(InsufficientBalanceException.class)
    public ProblemDetail insufficientBalance(InsufficientBalanceException e){
        var detail =  ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setDetail(e.getMessage());
        return detail;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ProblemDetail insufficientBalance(OrderNotFoundException e){
        var detail =  ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setDetail(e.getMessage());
        return detail;
    }
}
