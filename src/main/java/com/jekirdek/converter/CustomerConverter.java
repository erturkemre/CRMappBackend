package com.jekirdek.converter;

import com.jekirdek.dto.request.CustomerRequest;
import com.jekirdek.dto.response.CustomerResponse;
import com.jekirdek.dto.response.UserResponse;
import com.jekirdek.entity.Customer;
import com.jekirdek.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConverter {
    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .region(customer.getRegion())
                .registrationDate(customer.getRegistrationDate())
                .userId(customer.getUserId())
                .build();
    }

    public static List<CustomerResponse> toResponse(List<Customer> customers) {
        return customers.stream().map(CustomerConverter::toResponse).toList();
    }

    public static Customer toEntity(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setRegion(customerRequest.getRegion());
        customer.setRegistrationDate(customerRequest.getRegistrationDate());

        if (customerRequest.getUserId() != null) {
            customer.setUserId(customerRequest.getUserId());
        }
        return customer;
    }


}