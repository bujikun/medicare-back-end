package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.CartDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.OrderDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Order;
import com.bujikun.fsd.capstone.eHealthcare.entity.OrderItem;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.order.InsufficientBalanceException;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.order.OrderNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.repository.OrderRepository;
import com.bujikun.fsd.capstone.eHealthcare.repository.UserRepository;
import com.bujikun.fsd.capstone.eHealthcare.security.model.SecurityUserDetails;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IBaseService<Order, UUID> {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DateUtil dateUtil;
    private Function<ProductDTO, OrderItem> productDTOOrderItem = (productDTO -> OrderItem.builder()
            .productName(productDTO.getName())
            .quantity(productDTO.getCount())
            .unitPrice(productDTO.getPrice())
            .totalOrderItemPrice(productDTO.getPrice().multiply(BigDecimal.valueOf(productDTO.getCount())))
            .build());
    private BigDecimal getTotalPrice(CartDTO cartDTO){
       return cartDTO.getItems()
                .stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getCount())))
                .reduce(BigDecimal.valueOf(0), (prev, curr) -> prev.add(curr));
    }

    public List<OrderDTO> getOrdersByUserId(UUID id){
        return orderRepository.getOrdersByCustomerId(id)
                .stream()
                .map(o->OrderDTO.fromOrder(o,dateUtil))
                .toList();
    }
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(UUID uuid) {
        return Optional.empty();
    }
    public OrderDTO findOneById(UUID id) {

        return orderRepository.findById(id).map(o->OrderDTO.fromOrder(o,dateUtil))
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found"));
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    public OrderDTO create(CartDTO cartDTO) {
        var user = userRepository.findUserByUsername(cartDTO.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Invalid user"));
        var totalPrice = getTotalPrice(cartDTO);
        if(totalPrice.compareTo(user.getBalance()) == 1){
            throw new InsufficientBalanceException("No Enough Balance");
        }
        user.setBalance(user.getBalance().subtract(totalPrice));

        var order = Order.builder()
                .customerName(cartDTO.getName())
                .customerId(user.getId())
                .deleted(false)
                .totalPrice(totalPrice)
                .orderItems(cartDTO.getItems().stream().map(productDTOOrderItem).collect(Collectors.toSet()))
                .createdOn(dateUtil.now())
                .build();
        userRepository.save(user);
       order = orderRepository.save(order);
        var dto =  OrderDTO.fromOrder(order,dateUtil);
        return dto;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public Integer getCount() {
        return orderRepository.getCount();
    }
}
