package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("product_name")
    private String productName;
    @Column("unit_price")
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    @Column("quantity")
    @JsonProperty("quantity")
    private Integer quantity;
    @Transient
    @JsonProperty("total_order_item_price")
    private BigDecimal totalOrderItemPrice;
}
