package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.CategoryDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.SellerDTO;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;
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
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final DateUtil dateUtil;
    @Qualifier("JSON_PATCH_OBJECT_MAPPER")
    private final ObjectMapper objectMapper;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok().body(categoryService.getAll());
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        var newCategory = categoryService.create(categoryDTO);
        return ResponseEntity.ok().body(CategoryDTO.fromCategory(newCategory,dateUtil));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<CategoryDTO> findOne(@PathVariable("id")UUID uuid){
        var category = categoryService.findById(uuid).orElseThrow(()->new BaseException("Category Not Found"));
        return ResponseEntity.ok().body(CategoryDTO.fromCategory(category,dateUtil));
    }

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") UUID uuid,
                                       @RequestBody JsonPatch jsonPatch){
        //fetch category
        var category = categoryService.findById(uuid).orElseThrow(()->new BaseException("Category Not Found"));
        //apply patch
        var patchedCategory = category.patch(jsonPatch,objectMapper,category.getClass());
        categoryService.save(patchedCategory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
