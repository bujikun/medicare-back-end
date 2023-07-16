package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "order_items")
public class OrderItem {

    @Column("product_name")
    private String productName;
    @Column("unit_price")
    private BigDecimal unitPrice;
    @Column("quantity")
    private Integer quantity;
    @Transient
    private BigDecimal totalOrderItemPrice;
}
