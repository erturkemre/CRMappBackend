package com.jekirdek.service;

import com.jekirdek.converter.CustomerConverter;
import com.jekirdek.dto.request.CustomerRequest;
import com.jekirdek.dto.response.CustomerResponse;
import com.jekirdek.entity.Customer;
import com.jekirdek.exception.ErrorException;
import com.jekirdek.repository.CustomerRepository;

import com.jekirdek.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;


    public CustomerResponse addCustomer(String token, CustomerRequest customerRequest) {
        Customer customer = CustomerConverter.toEntity(customerRequest);
        Long userId = jwtUtil.extractUserId(token);
        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);
        log.info("role"+role);
        log.info("email"+email);
        log.info("userId:"+userId);
        customer.setUserId(userId);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerConverter.toResponse(savedCustomer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ErrorException("Customer not found"));

        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setRegion(customerRequest.getRegion());
        customer.setRegistrationDate(customerRequest.getRegistrationDate());

        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerConverter.toResponse(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ErrorException("Customer not found"));
        return CustomerConverter.toResponse(customer);
    }


    public List<CustomerResponse> getAllCustomer() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
        }

        if (jwtToken == null) {
            throw new ErrorException("JWT token not found in request headers");
        }
        log.info(jwtToken);
        Long userId = jwtUtil.extractUserId(jwtToken);
        String userRole = jwtUtil.extractRole(jwtToken);
        log.info(userRole);
        List<Customer> customers;

        if ("ADMIN".equals(userRole)) {

            customers = customerRepository.findAll();
        } else {

            customers = customerRepository.findByCreatedBy(userId);
        }


        return CustomerConverter.toResponse(customers);
    }

    public List<CustomerResponse> filterCustomers(String firstName, String lastName, String email, LocalDateTime startDate, LocalDateTime endDate, String region) {

        List<Customer> customers = customerRepository.filterCustomers(firstName, lastName, email, startDate, endDate, region);


        return customers.stream()
                .map(CustomerConverter::toResponse)
                .collect(Collectors.toList());
    }
}
