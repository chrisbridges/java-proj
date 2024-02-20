package com.example.authservice.controller;

import com.example.authservice.model.User;
import com.example.authservice.security.JwtTokenProvider;
import com.example.authservice.service.UserService;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; //TODO: FIX

@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {
    User registered = userService.register(user);
    String token = tokenProvider.createToken(registered.getUsername());
    Map<String, String> tokenResponse = Collections.singletonMap("token", token);

    return ResponseEntity.ok(tokenResponse);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
    if (userService.checkCredentials(username, password)) {
      String token = tokenProvider.createToken(username);
      Map<String, String> tokenResponse = Collections.singletonMap("token", token);

      return ResponseEntity.ok(tokenResponse);
    }
    return ResponseEntity.status(401).build();
  }
}
