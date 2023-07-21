package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.SellerDTO;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.product.ProductNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<List<SellerDTO>> findAll(){
        return ResponseEntity.ok().body(sellerService.getAll());
    }
}
