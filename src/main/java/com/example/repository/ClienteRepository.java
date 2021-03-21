package com.example.repository;

import com.example.entity.Cliente;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface ClienteRepository extends MongoRepository<Cliente, String>{
    
}
