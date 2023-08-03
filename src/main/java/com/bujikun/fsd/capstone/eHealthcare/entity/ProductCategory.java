package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.bujikun.fsd.capstone.eHealthcare.mapping.jdbc.CustomAggregateReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

/**
 * @author Newton Bujiku
 * @since 2023
 */
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "products_categories")
public class ProductCategory extends NonAggregateEntity<Category,UUID>{
    @Column("fk_category_id")
    private CustomAggregateReference<Category, UUID> categoryId;

    public ProductCategory() {
    }
}
