package com.example.asap_log_deasafio.helpers;

import java.io.Serializable;

import com.example.asap_log_deasafio.entity.Apolice;

import lombok.Data;

//Essa classe representa a respota se apolice esta vencidada
@Data
public class ApoliceStatus implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Apolice apolice;

    private Boolean apoliceVencida;

    private Long diasVencidos;

    private Long diasParaVencimentos;
}
