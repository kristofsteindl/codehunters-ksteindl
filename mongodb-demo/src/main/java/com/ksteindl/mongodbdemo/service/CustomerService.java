package com.ksteindl.mongodbdemo.service;

import com.ksteindl.mongodbdemo.repository.CustomerDocument;
import com.ksteindl.mongodbdemo.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
    
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer saveCustomer(Customer newCustomer) {
        if (newCustomer.getId() != null) {
            throw new RuntimeException("id must be null");
        }
        CustomerDocument customerDocument = customerMapper.convert(newCustomer);
        customerDocument.setId(UUID.randomUUID().toString());
        customerDocument.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
        CustomerDocument savedDocument = customerRepository.save(customerDocument);
        return customerMapper.convert(savedDocument);
    }
    
}
