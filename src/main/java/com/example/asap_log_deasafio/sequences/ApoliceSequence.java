package com.example.asap_log_deasafio.sequences;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "database_sequences")
@Data
public class ApoliceSequence {

    @Id
    private String id;

    private long seq;

}
