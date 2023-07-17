package com.bujikun.fsd.capstone.eHealthcare.exceptions;

import lombok.*;
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Setter
public class BaseException extends RuntimeException{
    protected  String message;
}
