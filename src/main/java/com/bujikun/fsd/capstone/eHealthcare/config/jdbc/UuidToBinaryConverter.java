package com.bujikun.fsd.capstone.eHealthcare.config.jdbc;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
@WritingConverter
@NoArgsConstructor
public class UuidToBinaryConverter implements Converter<UUID,byte[]> {
    @Override
    public byte[] convert(UUID source) {
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(source.getMostSignificantBits())
                .putLong(source.getLeastSignificantBits());
        return uuidBytes;
    }
}
