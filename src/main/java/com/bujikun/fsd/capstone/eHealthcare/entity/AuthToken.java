package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "tokens")
public class AuthToken extends BaseEntity{
    @Column("value")
    private String value;
    @Column("active")
    private Boolean active;
    private String username;
}
