package com.example.asap_log_deasafio.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Clientes")
public class Cliente {

    @Id
    String id;

    String nome;

    String cpf;

    String cidade;

    String uf;
}