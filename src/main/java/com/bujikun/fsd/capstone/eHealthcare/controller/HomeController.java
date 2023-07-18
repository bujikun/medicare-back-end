package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.entity.BaseEntity;
import com.bujikun.fsd.capstone.eHealthcare.service.CategoryService;
import com.bujikun.fsd.capstone.eHealthcare.service.HomeService;
import com.bujikun.fsd.capstone.eHealthcare.service.ProductService;
import com.nimbusds.jose.shaded.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<HashMap<String,String>>> fetchHome(){
        return ResponseEntity.ok()
                .body(homeService.fetchHomeItems());
    }

}
