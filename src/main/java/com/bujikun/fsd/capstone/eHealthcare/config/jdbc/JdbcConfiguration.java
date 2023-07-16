package com.bujikun.fsd.capstone.eHealthcare.config.jdbc;

import com.bujikun.fsd.capstone.eHealthcare.entity.BaseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.List;
import java.util.UUID;

@Configuration
public class JdbcConfiguration extends AbstractJdbcConfiguration {
    @Bean
    public BeforeConvertCallback<BaseEntity> convertCallback(){
        return entity ->{
            if(entity.getId() == null){
                entity.setId(UUID.randomUUID());
            }
            return entity;
        };
    }
    @Override
    protected List<?> userConverters() {
        return List.of(new BinaryToUuidConverter(),new UuidToBinaryConverter());
    }
}
