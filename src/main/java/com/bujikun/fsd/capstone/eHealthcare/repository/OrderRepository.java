package com.bujikun.fsd.capstone.eHealthcare.repository;


import com.bujikun.fsd.capstone.eHealthcare.entity.Order;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepository  extends BaseRepository<Order, UUID> {
    List<Order> getOrdersByCustomerId(UUID id);
    @Query("SELECT COUNT(*) FROM orders")
    Integer getCount();

}
