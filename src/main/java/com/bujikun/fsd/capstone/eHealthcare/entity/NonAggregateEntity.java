package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public  class NonAggregateEntity<T,U> {
    @CreatedDate
    @Column("created_on")
    private Instant createdOn;
    @LastModifiedDate
    @Column("updated_on")
    private Instant updatedOn;
    @Column("deleted")
    private Boolean deleted;
}
