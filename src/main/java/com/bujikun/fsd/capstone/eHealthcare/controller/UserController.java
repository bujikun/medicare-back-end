package com.bujikun.fsd.capstone.eHealthcare.controller;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.OrderDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.UserDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.User;
import com.bujikun.fsd.capstone.eHealthcare.service.UserService;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
private final UserService userService;
private final DateUtil dateUtil;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll()
                        .stream()
                        .map(o -> UserDTO.fromUser(o, dateUtil))
                        .toList()
                );
    }
    @PostMapping
    public ResponseEntity<Map<String,String>> register(@RequestBody User user){
        userService.save(user);
        var response = new HashMap<String,String>();
        response.put("status","201");
        response.put("Success","User Successfully Registered");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id")UUID id){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.findOneById(id));
    }

}
