package com.svistun.vkontakte.controller;

import com.svistun.vkontakte.service.serviceImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> userPage() {
        try {
            return new ResponseEntity<>(userService.getUserById(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }






}
