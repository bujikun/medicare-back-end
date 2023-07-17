package com.bujikun.fsd.capstone.eHealthcare.config.jdbc;

import com.bujikun.fsd.capstone.eHealthcare.entity.NonAggregateEntity;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

@WritingConverter
@NoArgsConstructor
public class AggregateReferenceToBinaryConverter<T extends NonAggregateEntity> implements
        Converter<AggregateReference<T,UUID>, byte[]> {
    @Override
    public byte[] convert(AggregateReference<T, UUID> source) {
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(source.getId().getMostSignificantBits())
                .putLong(source.getId().getLeastSignificantBits());
        return uuidBytes;
    }
}
