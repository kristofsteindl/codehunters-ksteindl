package com.ksteindl.mongodbdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDocument, String> {

    Optional<CustomerDocument> findByUsername(String username);

}