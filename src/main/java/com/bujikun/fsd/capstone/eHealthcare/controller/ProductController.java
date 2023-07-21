package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Product;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.product.ProductNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO){
        return  ResponseEntity.ok().body(productService.create(productDTO));
    }
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> update(@RequestBody ProductDTO productDTO){

        return  ResponseEntity.accepted().body(productService.updateProduct(productDTO)?"{'success':true}":"{'success':false}");
    }
    @PutMapping("/image")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateImageUrl(@RequestBody ProductDTO productDTO){
        return  ResponseEntity.accepted().body(productService.updateProductImage(productDTO)?"{'success':true}":"{'success':false}");
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok().body(productService.findAllWithCategory());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ProductDTO> findOne(@PathVariable("id")String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
           throw new ProductNotFoundException("Product with ID: "+id+" " +
                    "could not be found");
        }
        return ResponseEntity.ok()
                .body(productService.findOneById(uuid)
                        .orElseThrow(()->new ProductNotFoundException("Product with ID: "+uuid+" " +
                                "could not be found"))
                );
    }
}
