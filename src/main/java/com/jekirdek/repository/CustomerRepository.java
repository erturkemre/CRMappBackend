package com.jekirdek.repository;

import com.jekirdek.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c " +
            "WHERE (:firstName IS NULL OR (c.firstName LIKE %:firstName% OR c.lastName LIKE %:firstName%)) " +
            "AND (:lastName IS NULL OR c.lastName LIKE %:lastName%) " +
            "AND (:email IS NULL OR c.email LIKE %:email%) " +
            "AND (:startDate IS NULL OR c.registrationDate >= :startDate) " +
            "AND (:endDate IS NULL OR c.registrationDate <= :endDate) " +
            "AND (:region IS NULL OR c.region LIKE %:region%)")
    List<Customer> filterCustomers(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName,
                                   @Param("email") String email,
                                   @Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate,
                                   @Param("region") String region);
}
