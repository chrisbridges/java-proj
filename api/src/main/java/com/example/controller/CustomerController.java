package com.example.controller;

import com.example.model.Customer;
import com.example.customerService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

  // Create a new Customer
  @PostMapping
  public Customer createCustomer(@RequestBody Customer customer) {
    return customerRepository.save(customer);
  }

  // Get a Single Customer
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) {
    Optional<Customer> customer = customerRepository.findById(customerId);
    return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Update a Customer
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
      @RequestBody Customer customerDetails) {
    Optional<Customer> customerOptional = customerRepository.findById(customerId);
    if (!customerOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    Customer customer = customerOptional.get();
    customer.setName(customerDetails.getName());
    customer.setEmail(customerDetails.getEmail());
    Customer updatedCustomer = customerRepository.save(customer);
    return ResponseEntity.ok(updatedCustomer);
  }

  // Delete a Customer
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long customerId) {
    return customerRepository.findById(customerId)
        .map(customer -> {
          customerRepository.delete(customer);
          return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
