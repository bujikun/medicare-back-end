package com.bujikun.fsd.capstone.eHealthcare.config.jdbc;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.nio.ByteBuffer;
import java.util.UUID;

@ReadingConverter
@NoArgsConstructor
public class BinaryToUuidConverter implements Converter<byte[], UUID> {
    @Override
    public UUID convert(byte[] source) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(source);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();
        return new UUID(high,low);
    }
}
