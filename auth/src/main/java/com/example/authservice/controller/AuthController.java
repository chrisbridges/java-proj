package main.java.com.example.authservice.controller;

import main.java.com.example.authservice.model.User;
import main.java.com.example.authservice.security.JwtTokenProvider;
import main.java.com.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    User registeredUser = userService.registerUser(user);
    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()));

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(jwt);
  }
}
