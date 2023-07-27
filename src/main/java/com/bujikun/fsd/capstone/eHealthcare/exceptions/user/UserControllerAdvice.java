package com.bujikun.fsd.capstone.eHealthcare.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserExistsException.class)
    public ProblemDetail userExists(UserExistsException e){
        var detail =  ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setDetail(e.getMessage());
        return detail;
    }
}
