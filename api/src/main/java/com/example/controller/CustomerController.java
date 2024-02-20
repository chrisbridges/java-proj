package com.example.controller;

import com.example.model.Customer;
import com.example.customerService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }
}
