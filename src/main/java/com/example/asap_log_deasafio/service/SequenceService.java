package com.example.asap_log_deasafio.service;

import com.example.asap_log_deasafio.sequences.ApoliceSequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceService {
    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        ApoliceSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().set("seq", Random), options().returnNew(true).upsert(true), ApoliceSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
