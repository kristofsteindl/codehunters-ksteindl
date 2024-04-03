package com.ksteindl.mongodbdemo.service;

import com.ksteindl.mongodbdemo.repository.CustomerDocument;
import com.ksteindl.mongodbdemo.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public UserDetailsServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerDocument customerDocument = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found"));
        return customerMapper.convert(customerDocument);
        
        
    }
}
