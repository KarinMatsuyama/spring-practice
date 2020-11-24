package com.example.springpractice.dao;

import com.example.springpractice.model.Customer;

import java.util.UUID;

public interface CustomerDao {
    int insertCustomer(UUID id, Customer customer);

    default int addCustomer(Customer customer) {
        UUID id = UUID.randomUUID();
        return insertCustomer(id, customer);
    }
}
