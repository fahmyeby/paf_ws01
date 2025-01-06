package com.example.paf_ws1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.paf_ws1.model.Customer;
import com.example.paf_ws1.repo.CustomerRepo;

@Service
public class CustomerService {
    @Autowired private CustomerRepo customerRepo;

    public List<Customer> getAllCustomers(int offset, int limit){
        return customerRepo.getAllCustomers(offset, limit);
    }

    public Customer getCustomerById(int id){
        Optional<Customer> optionalCustomer = customerRepo.getById(id);
        if (optionalCustomer.isPresent()){
            return optionalCustomer.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer ID not found");
        }
    }
}
