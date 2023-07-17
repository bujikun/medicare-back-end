package com.bujikun.fsd.capstone.eHealthcare.config.jdbc;

import com.bujikun.fsd.capstone.eHealthcare.entity.NonAggregateEntity;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@ReadingConverter
@NoArgsConstructor
public class TimeToStringConverter implements  Converter<LocalDateTime,String> {
    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";

    @Override
    public String convert(LocalDateTime source) {
        //spring reads from db as localdatetime instead of instant
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return formatter.format(source);
    }
}
