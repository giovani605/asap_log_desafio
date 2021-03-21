package com.example.asap_log_deasafio.entity;

import java.io.Serializable;

import org.springframework.core.serializer.Serializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Clientes")
public class Cliente implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2872995689699638240L;

    @Id
    private String id;

    private String nome;

    private String cpf;

    private String cidade;

    private String uf;
}