package com.GMH.digital.BarberPub.by.GMH.controllers;

import com.GMH.digital.BarberPub.by.GMH.dto.CustomerDTO;
import com.GMH.digital.BarberPub.by.GMH.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/me")
    public CustomerDTO getCurrentCustomer() {
        return new CustomerDTO(customerService.getCurrentCustomer());
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateCurrentCustomer(@RequestBody CustomerDTO dto) {
        customerService.updateCurrentCustomer(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerDTO dto) {
        customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable long id, @RequestBody CustomerDTO dto) {
        customerService.updateCustomer(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
