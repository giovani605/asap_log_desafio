package com.example.asap_log_deasafio;

import java.util.List;
import java.util.Optional;

import com.example.asap_log_deasafio.entity.Apolice;
import com.example.asap_log_deasafio.helpers.ApoliceStatus;
import com.example.asap_log_deasafio.service.ApoliceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/apolices")
@Api("Api de apolices")
public class ApoliceController {

    @Autowired
    ApoliceService apoliceService;

    @ApiOperation(value = "consulta todos os clientes")
    @GetMapping("")
    public ResponseEntity<List<Apolice>> getAll() throws Exception {
        List<Apolice> items = apoliceService.consultarApolices();

        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Apolice> getById(@PathVariable("id") String id) throws Exception {
        Optional<Apolice> existingItemOptional = apoliceService.consultarApoliceId(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("consultar/{id}")
    public ResponseEntity<ApoliceStatus> gerarRelatorio(@PathVariable("id") Long numeroApolice) throws Exception {
        ApoliceStatus apolice = apoliceService.consultarResultadoApolice(numeroApolice);
        return new ResponseEntity<>(apolice, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Apolice> createApolice(@RequestBody Apolice item) throws Exception {
        Apolice savedItem = apoliceService.salvarApolice(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Apolice> update(@RequestBody Apolice cliente) throws Exception {
        Apolice savedItem = this.apoliceService.atualizarApolice(cliente);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) throws Exception {
        apoliceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
