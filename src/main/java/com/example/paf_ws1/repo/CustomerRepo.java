package com.example.paf_ws1.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.paf_ws1.model.Customer;

@Repository
public class CustomerRepo {
    @Autowired JdbcTemplate template;

    // sql queries
    private static final String FIND_ALL_CUSTOMERS = "SELECT * FROM customers LIMIT ? OFFSET ?";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";

    // get all customers
    public List<Customer> getAllCustomers(int offset, int limit){
        SqlRowSet rs = template.queryForRowSet(FIND_ALL_CUSTOMERS, limit, offset);
        List<Customer> customers = new LinkedList<>();
        while(rs.next()){
            customers.add(Customer.toCustomer(rs));
        } return customers;
    }

    // get by id
    public Optional<Customer> getById(int id){
        SqlRowSet rs = template.queryForRowSet(FIND_CUSTOMER_BY_ID, id);
        if(rs.next()){
            return Optional.of(Customer.toCustomer(rs));
        } return Optional.empty();
    }
}
