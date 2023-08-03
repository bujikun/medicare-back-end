package com.bujikun.fsd.capstone.eHealthcare.mapping.jdbc;

import com.bujikun.fsd.capstone.eHealthcare.entity.NonAggregateEntity;
import lombok.*;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.util.Assert;

@Getter
@Setter
@EqualsAndHashCode
public class CustomAggregateReference<T,ID> implements AggregateReference<T,ID> {
    private  ID id;

    public CustomAggregateReference() {
    }

    public CustomAggregateReference(ID id) {
        Assert.notNull(id,"Id can not be null");
        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }

}
