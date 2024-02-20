package main.java.com.example.authservice.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  // Default constructor for JPA
  public User() {
  }

  // All-args constructor for convenience
  public User(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  // Convenience method for updating password
  public void updatePassword(String newPassword) {
    this.password = newPassword;
  }
}
