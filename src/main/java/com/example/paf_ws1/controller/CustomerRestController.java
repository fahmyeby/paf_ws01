package com.example.paf_ws1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.paf_ws1.model.Customer;
import com.example.paf_ws1.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(
        @RequestParam(defaultValue = "0") int offset,
        @RequestParam(defaultValue = "10") int limit
    ) {
        return customerService.getAllCustomers(offset, limit);
    }

    @GetMapping("/customer/{id}")
    public Customer getById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }
}

// testing urls
// http://localhost:8080/api/customers?offset=0&limit=5 -- to get all, input numbers in offset=<> and limit=<>
// http://localhost:8080/api/customer/1 -- by customer id, input in customer/<id>
