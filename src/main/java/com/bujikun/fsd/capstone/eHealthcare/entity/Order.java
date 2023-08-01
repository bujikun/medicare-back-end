package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "orders")
public class Order extends BaseEntity{
    @Column("customer_name")
    private String customerName;
    @Column("fk_user_id")
    private UUID customerId;
    @Column("total_price")
    private BigDecimal totalPrice;
    @MappedCollection(idColumn = "fk_order_id")
    private Set<OrderItem> orderItems;
}
