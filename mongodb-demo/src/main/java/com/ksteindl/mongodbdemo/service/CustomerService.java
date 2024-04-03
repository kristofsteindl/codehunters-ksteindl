package com.ksteindl.mongodbdemo.service;

import com.ksteindl.mongodbdemo.repository.CustomerDocument;
import com.ksteindl.mongodbdemo.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    
    public Customer updateCustomer(Customer updatedCustomer) {
        String id = updatedCustomer.getId();
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        CustomerDocument customerDocument = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no customer was found for id"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("user is not authenticated");
        }
        User user = (User) authentication.getPrincipal();
        if (user == null || user.getUsername().equals(updatedCustomer.getUsername())) {
            throw new IllegalStateException("logged in user and updated user is not the same");
        }
        CustomerDocument updatedDocument = customerMapper.convert(updatedCustomer);
        updatedDocument.setPassword(customerDocument.getPassword());
        CustomerDocument savedDocument = customerRepository.save(customerDocument);
        return customerMapper.convert(savedDocument);
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
