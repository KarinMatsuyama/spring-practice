package com.example.springpractice.controller;

import com.example.springpractice.dao.CustomerDao;
import com.example.springpractice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean CustomerDao customerDao;

    private List<Customer> customerList;

    @BeforeEach
    void setUp() {
        this.customerList = new ArrayList<>();
        this.customerList.add(new Customer(1, "Karin", "m.karin2@gmail.com"));
        this.customerList.add(new Customer(2, "Gary", "gary@email.com"));
    }

    @Test
    void getCustomers() throws Exception {
        given(customerDao.findAll()).willReturn(customerList);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(customerList.size())));
    }
}