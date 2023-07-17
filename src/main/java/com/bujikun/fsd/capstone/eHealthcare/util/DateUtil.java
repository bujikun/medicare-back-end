package com.bujikun.fsd.capstone.eHealthcare.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class DateUtil {
    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";
    public  String fromLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
    public  LocalDateTime fromString(String timeString){
        return LocalDateTime.parse(timeString,DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }

    public Instant now(){
        return Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }
    public static Instant getNow(){
        return Instant.now().truncatedTo(ChronoUnit.SECONDS);
    }

}
