package com.example;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner initDatabase(CustomerRepository repository) {
    return args -> {
      repository.save(new Customer(null, "Walt", "walt@example.com"));
      repository.save(new Customer(null, "Sol", "sol@example.com"));
      repository.save(new Customer(null, "Ethan", "ethan@example.com"));
      repository.save(new Customer(null, "Kenny", "kenny@example.com"));
      repository.save(new Customer(null, "Skylar", "skylar@example.com"));
    };
  }
}
