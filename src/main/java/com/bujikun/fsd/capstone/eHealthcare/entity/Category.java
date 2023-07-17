package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Newton Bujiku
 * @since 2023
 */


@Getter
@Setter
@SuperBuilder
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column
    private String name;
}
