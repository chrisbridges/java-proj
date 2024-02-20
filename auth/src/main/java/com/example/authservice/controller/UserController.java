package main.java.com.example.authservice.controller;

import main.java.com.example.authservice.model.User;
import main.java.com.example.authservice.security.JwtTokenProvider;
import main.java.com.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    return ResponseEntity.ok(token);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
    if (userService.checkCredentials(username, password)) {
      String token = tokenProvider.createToken(username);
      return ResponseEntity.ok(token);
    }
    return ResponseEntity.status(401).build();
  }
}
