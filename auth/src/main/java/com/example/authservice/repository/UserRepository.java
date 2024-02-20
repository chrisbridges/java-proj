package main.java.com.example.authservice.repository;

import main.java.com.example.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
