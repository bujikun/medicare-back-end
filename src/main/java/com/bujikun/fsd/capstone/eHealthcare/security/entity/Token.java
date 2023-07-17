package com.bujikun.fsd.capstone.eHealthcare.security.entity;

import com.bujikun.fsd.capstone.eHealthcare.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "tokens")
public class Token extends BaseEntity {
    @Column("value")
    private String value;
    @Column("username")
    private String username;
    @Column("invalidated")
    private Boolean isInvalidated;
    @Transient
    private Instant updatedOn;
}