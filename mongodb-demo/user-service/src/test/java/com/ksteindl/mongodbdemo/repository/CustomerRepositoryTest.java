package com.ksteindl.mongodbdemo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repo;
    
    @BeforeEach
//    @AfterEach
    public void init() {
        repo.deleteAll();
    }
    
    @Test
    void test() {
        repo.save(new CustomerDocument("hello", "world"));
        var customers = repo.findAll();
        Assertions.assertEquals(1, customers.size());
    }

   
}