package com.example.springpractice.controller;

import com.example.springpractice.dao.CustomerDao;
import com.example.springpractice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerDao dao;

    @RequestMapping("/customers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return dao.findAll();
    }
}
