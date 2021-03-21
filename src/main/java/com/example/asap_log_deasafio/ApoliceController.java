package com.example.asap_log_deasafio;

import java.util.List;
import java.util.Optional;

import com.example.asap_log_deasafio.entity.Apolice;
import com.example.asap_log_deasafio.service.ApoliceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/apolices")
@Api("Api de apolices")
public class ApoliceController {

    @Autowired
    ApoliceService apoliceService;

    @ApiOperation(value = "consulta todos os clientes")
    @GetMapping("")
    public ResponseEntity<List<Apolice>> getAll() {
        try {
            List<Apolice> items = apoliceService.consultarApolices();

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Apolice> getById(@PathVariable("id") Long id) {
        Optional<Apolice> existingItemOptional = apoliceService.consultarApoliceId(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Apolice> create(@RequestBody Apolice item) throws Exception {
        try {
            Apolice savedItem = apoliceService.salvarApolice(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("")
    public ResponseEntity<Apolice> update(@RequestBody Apolice cliente) {
        try {
            Apolice savedItem = this.apoliceService.atualizarApolice(cliente);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            apoliceService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
