package com.example.asap_log_deasafio.repository;

import java.util.List;

import com.example.asap_log_deasafio.entity.Cliente;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    List<Cliente> findByCpf(String cpf);

    List<Cliente> findByCpfAndIdNot(String cpf, String id);

}
