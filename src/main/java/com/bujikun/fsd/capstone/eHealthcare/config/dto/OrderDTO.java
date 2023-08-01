package com.bujikun.fsd.capstone.eHealthcare.config.dto;

import com.bujikun.fsd.capstone.eHealthcare.entity.BaseEntity;
import com.bujikun.fsd.capstone.eHealthcare.entity.Order;
import com.bujikun.fsd.capstone.eHealthcare.entity.OrderItem;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("customer_id")
    private UUID customerId;
    @JsonProperty("total_price")
    private BigDecimal totalPrice;
    @JsonProperty("order_items")
    private Set<OrderItem> orderItems;
    @JsonProperty("created_on")
    private String createdOn;
    private UUID id;


    public static  OrderDTO fromOrder(Order order, DateUtil dateUtil){
        return OrderDTO.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .totalPrice(order.getTotalPrice())
                .orderItems(order.getOrderItems())
                .customerId(order.getCustomerId())
                .createdOn(dateUtil.fromInstant(order.getCreatedOn()))
                .build();
    }
}
