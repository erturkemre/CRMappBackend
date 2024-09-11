package com.jekirdek.controller;

import com.jekirdek.dto.request.CustomerRequest;
import com.jekirdek.dto.response.CustomerResponse;
import com.jekirdek.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestHeader ("Authorization") String authorizationHeader, @RequestBody CustomerRequest customerRequest) {
        String token = authorizationHeader.substring(7);
        CustomerResponse createdCustomer = customerService.addCustomer(token, customerRequest);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/")
    public List<CustomerResponse> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CustomerResponse>> filterCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
            @RequestParam(required = false) String region) {

        List<CustomerResponse> customers = customerService.filterCustomers(firstName,lastName, email, startDate, endDate, region);
        return ResponseEntity.ok(customers);
    }

}
