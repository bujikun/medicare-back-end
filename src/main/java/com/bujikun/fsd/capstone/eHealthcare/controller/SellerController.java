package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.CategoryDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.SellerDTO;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.product.ProductNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.service.CategoryService;
import com.bujikun.fsd.capstone.eHealthcare.service.SellerService;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonPatch;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    private final DateUtil dateUtil;
    @Qualifier("JSON_PATCH_OBJECT_MAPPER")
    private final ObjectMapper objectMapper;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SellerDTO> create(@RequestBody SellerDTO sellerDTO) {
        var newSeller = sellerService.create(sellerDTO);
        return ResponseEntity.ok().body(SellerDTO.fromSeller(newSeller,dateUtil));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SellerDTO> findOne(@PathVariable("id")UUID uuid){
        var seller = sellerService.findById(uuid).orElseThrow(()->new BaseException("Seller Not Found"));
        return ResponseEntity.ok().body(SellerDTO.fromSeller(seller,dateUtil));
    }

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID uuid,
                                       @RequestBody JsonPatch jsonPatch){
        //fetch sller
        var seller = sellerService.findById(uuid).orElseThrow(()->new BaseException("Seller Not Found"));
        //apply patch
        var patcheSeller = seller.patch(jsonPatch,objectMapper,seller.getClass());
        sellerService.save(patcheSeller);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<List<SellerDTO>> findAll(){
        return ResponseEntity.ok().body(sellerService.getAll());
    }
}
