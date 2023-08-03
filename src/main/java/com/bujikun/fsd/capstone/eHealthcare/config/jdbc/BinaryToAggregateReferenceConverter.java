package com.bujikun.fsd.capstone.eHealthcare.config.jdbc;

import com.bujikun.fsd.capstone.eHealthcare.entity.NonAggregateEntity;
import com.bujikun.fsd.capstone.eHealthcare.mapping.jdbc.CustomAggregateReference;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

@ReadingConverter
@NoArgsConstructor
public class BinaryToAggregateReferenceConverter<T extends NonAggregateEntity>
        implements Converter<byte[], CustomAggregateReference<T,UUID>> {
    @Override
    public CustomAggregateReference<T,UUID> convert(byte[] source) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(source);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();
        var uuid =  new UUID(high,low);
        return new CustomAggregateReference(uuid);
    }
}
