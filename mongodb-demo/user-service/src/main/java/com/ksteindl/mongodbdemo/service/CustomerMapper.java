package com.ksteindl.mongodbdemo.service;

import com.ksteindl.mongodbdemo.repository.CustomerDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    
    Customer convert(CustomerDocument customerDocument);
    
    CustomerDocument convert(Customer customer);
    
}
