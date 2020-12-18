package com.example.springpractice.consumer;

import com.example.springpractice.dao.CustomerDao;
import com.example.springpractice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @Autowired
    CustomerDao dao;

    @KafkaListener(topics = "customerProfile", groupId = "group_id")
    public void consumeMessage(Customer customer) {
        System.out.println(customer);
        dao.save(customer);
    }
}
