package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.bujikun.fsd.capstone.eHealthcare.mapping.jdbc.CustomAggregateReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

/**
 * @author Newton Bujiku
 * @since 2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "products_sellers")
public class ProductSeller extends NonAggregateEntity<Seller,UUID>{
    @Column("fk_seller_id")
    private CustomAggregateReference<Seller, UUID> sellerId;

}
