package com.bujikun.fsd.capstone.eHealthcare.security.controller;

import com.bujikun.fsd.capstone.eHealthcare.security.dto.AuthDTO;
import com.bujikun.fsd.capstone.eHealthcare.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity<AuthDTO> create(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tokenService.generateToken(authentication));
    }

    @PostMapping("/invalidate")
    public ResponseEntity<String> invalidateToken(@RequestParam("username") String username){
        tokenService.invalidate(username);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Token invalidated");
    }

    @GetMapping("/introspect")
    public ResponseEntity<Boolean> introspect(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(true);
    }
}
