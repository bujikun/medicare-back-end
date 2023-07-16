package com.bujikun.fsd.capstone.eHealthcare.security.controller;

import com.bujikun.fsd.capstone.eHealthcare.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity<String> create(Authentication authentication){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(tokenService.generateToken(authentication));
    }

    @PostMapping("/invalidate")
    public ResponseEntity<String> invalidateToken(@RequestParam("username") String username){
        tokenService.invalidate(username);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Token invalidated");
    }
}
