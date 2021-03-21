package com.example.asap_log_deasafio.repository;

import java.util.List;

import com.example.asap_log_deasafio.entity.Apolice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApoliceRepository extends MongoRepository<Apolice, String> {

    public List<Apolice> findByNumeroApolice(Long numeroApolice);

}
