package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.entity.User;
import com.bujikun.fsd.capstone.eHealthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
private final UserService userService;
    @PostMapping
    public ResponseEntity<Map<String,String>> register(@RequestBody User user){
        userService.save(user);
        var response = new HashMap<String,String>();
        response.put("status","201");
        response.put("Success","User Successfully Registered");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
