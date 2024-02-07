package com.ksteindl.mongodbdemo.controller.dto;

import com.ksteindl.mongodbdemo.service.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    
    private Customer customer;
    
}
