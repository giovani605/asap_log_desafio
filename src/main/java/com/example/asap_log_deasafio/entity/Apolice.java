package com.example.asap_log_deasafio.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "apolices")
public class Apolice implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7105409970622558954L;

    @Id
    private String id;

    @Transient
    public static final String SEQUENCE_NAME = "apolices_sequence";

    private Long numeroApolice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataFim;

    private String placa;

    private Long valor;

    @DBRef
    private Cliente dono;

    @Transient
    private String idCliente;

}
