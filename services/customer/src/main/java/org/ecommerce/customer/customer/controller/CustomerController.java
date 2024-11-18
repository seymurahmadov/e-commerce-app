package org.ecommerce.customer.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.customer.customer.exception.CustomerNotFoundException;
import org.ecommerce.customer.customer.service.CustomerService;
import org.ecommerce.customer.customer.dto.CustomerRequest;
import org.ecommerce.customer.customer.dto.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
    return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) throws CustomerNotFoundException {
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/exits/{customer_id}")
    public ResponseEntity<Boolean> findCustomerExits(@PathVariable("customer_id") String customer_id) {
        return ResponseEntity.ok(customerService.existsById(customer_id));
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("customer_id") String customer_id) throws CustomerNotFoundException {
        return ResponseEntity.ok(customerService.findById(customer_id));
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer_id") String customer_id) throws CustomerNotFoundException {
        customerService.delete(customer_id);
        return ResponseEntity.accepted().build();
    }
}