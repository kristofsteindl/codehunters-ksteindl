package com.ksteindl.mongodbdemo.controller;

import com.ksteindl.mongodbdemo.controller.dto.CustomerRequest;
import com.ksteindl.mongodbdemo.controller.dto.CustomerResponse;
import com.ksteindl.mongodbdemo.service.Customer;
import com.ksteindl.mongodbdemo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/registration")
public class RegistrationRestController {
    
    private final CustomerService customerService;

    public RegistrationRestController(CustomerService customerService) {
        this.customerService = customerService;
    }
    

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer savedCustomer = customerService.saveCustomer(customerRequest.getCustomer());
        CustomerResponse customerResponse = new CustomerResponse(savedCustomer.getId());
        return ResponseEntity.status(201).body(customerResponse);
    }
    
    
    
}
