package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * @author Newton Bujiku
 * @since 2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "products_categories")
public class ProductCategory {
    @Column("fk_category_id")
    private AggregateReference<Permission, UUID> categoryId;
    @CreatedDate
    @Column("created_on")
    private Instant createdOn;
    @LastModifiedDate
    @Column("updated_on")
    private Instant updatedOn;
}
