package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.product.ProductNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonPatch;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private static final String APPLICATION_JSON_PATCH_JSON = "application/json-patch+json";
    private final ProductService productService;
    @Qualifier("JSON_PATCH_OBJECT_MAPPER")
    private final ObjectMapper objectMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok().body(productService.create(productDTO));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> update(@RequestBody ProductDTO productDTO) {

        return ResponseEntity.accepted().body(productService.updateProduct(productDTO) ? "{'success':true}" : "{'success':false}");
    }

    @PutMapping("/image")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateImageUrl(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.accepted().body(productService.updateProductImage(productDTO) ? "{'success':true}" : "{'success':false}");
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok().body(productService.findAllAvailable());
    }

    @GetMapping("/shop")
    public ResponseEntity<List<ProductDTO>> findAllForShopping() {
        return ResponseEntity.ok().body(productService.findAllWithCategory());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> search(@RequestParam("q") String queryString) {
        log.error(queryString);
        return ResponseEntity.ok().body(productService.search(queryString));
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ProductDTO> findOne(@PathVariable("id") String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new ProductNotFoundException("Product with ID: " + id + " " +
                    "could not be found");
        }
        return ResponseEntity.ok()
                .body(productService.findOneById(uuid)
                        .orElseThrow(() -> new ProductNotFoundException("Product with ID: " + uuid + " " +
                                "could not be found"))
                );
    }
    @PatchMapping(path = "/{id}", consumes = APPLICATION_JSON_PATCH_JSON)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> patchProduct(@PathVariable("id") UUID id,
                                             @RequestBody JsonPatch jsonPatch) {
        var product = productService.findOne(id);//fetch from db
        var patchedProduct = product.patch(jsonPatch, objectMapper, product.getClass());//apply patch
        productService.save(patchedProduct);//persist changes
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
