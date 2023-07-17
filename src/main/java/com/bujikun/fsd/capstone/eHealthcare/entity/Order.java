package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@SuperBuilder
@Table(name = "orders")
public class Order extends BaseEntity{
    @Column("order_number")
    private String orderNumber;
    @Column("customer_name")
    private String customerName;
    @Column("fk_user_id")
    private UUID customerId;
    @MappedCollection(idColumn = "fk_order_id")
    private Set<OrderItem> orderItems;
}
