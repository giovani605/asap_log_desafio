package com.example.asap_log_deasafio.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "apolices")
@Data
public class Apolice {

    @Id
    Long numeroApolice;
    Date dataInicio;
    Date dataFim;
    String placa;
    Long valor;

}
