package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "products")
public class Product extends BaseEntity{
    @Column("name")
    private String name;
    @Column("price")
    private BigDecimal price;
    @Column("img_url")
    private String imageUrl;
    private String description;
}
