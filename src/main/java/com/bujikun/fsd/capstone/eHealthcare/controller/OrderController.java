package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.CartDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.OrderDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Order;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.product.ProductNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.security.model.SecurityUserDetails;
import com.bujikun.fsd.capstone.eHealthcare.service.OrderService;
import com.bujikun.fsd.capstone.eHealthcare.service.ProductService;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final DateUtil dateUtil;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.findAll()
                        .stream()
                        .map(o -> OrderDTO.fromOrder(o, dateUtil))
                        .toList()
                );
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<OrderDTO> create(@RequestBody CartDTO cartDTO) {
        var response = ResponseEntity.ok()
                .body(orderService.create(cartDTO));
        return response;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@RequestParam("id") UUID id) {
        return ResponseEntity.ok().body(orderService.getOrdersByUserId(id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<OrderDTO> getOneOrder(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(orderService.findOneById(id));
    }

}
