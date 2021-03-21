package com.example.asap_log_deasafio.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.example.asap_log_deasafio.entity.Apolice;
import com.example.asap_log_deasafio.repository.ApoliceRepository;
import com.example.asap_log_deasafio.util.UtilValidador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApoliceService {

    @Autowired
    ApoliceRepository apoliceRepository;

    @Autowired
    SequenceService sequenceService;

    public List<Apolice> consultarApolices() {
        return apoliceRepository.findAll();
    }

    public Optional<Apolice> consultarApoliceId(String id) {
        return apoliceRepository.findById(id);
    }

    public Apolice atualizarApolice(Apolice apolice) throws Exception {
        Optional<Apolice> apoliceOpt = apoliceRepository.findById(apolice.getId());
        if (apoliceOpt.isPresent()) {
            validarApolice(apolice);
            // não deixa o usuario editar esse numero de apolice
            apolice.setNumeroApolice(apoliceOpt.get().getNumeroApolice());
            return apoliceRepository.save(apolice);
        } else {
            throw new Exception("Este registro não existe no banco.");
        }

    }

    private void validarApolice(Apolice apolice) throws Exception {

        if (!UtilValidador.isValidString(apolice.getDataFim())) {
            throw new Exception("Data fim inválida.");
        }
        if (!UtilValidador.isValidString(apolice.getDataInicio())) {
            throw new Exception("Data inicio inválida.");
        }

        if (!UtilValidador.isValidString(apolice.getValor())) {
            throw new Exception("Valor obrigatório.");
        }

        if (!UtilValidador.isValidString(apolice.getPlaca())) {
            throw new Exception("Placa obrigatória.");
        }

    }

    private void gerarNumeroApolice(Apolice apolice) {

        // Gera o numero de apolice aleatorio e testa se está disponivel
        do {
            apolice.setNumeroApolice(ThreadLocalRandom.current().nextLong(10, 100000000));
        } while (apoliceRepository.findByNumeroApolice(apolice.getNumeroApolice()).size() > 0);

    }

    public Apolice salvarApolice(Apolice apolice) throws Exception {
        validarApolice(apolice);
        gerarNumeroApolice(apolice);
        return apoliceRepository.save(apolice);
    }

    public void deleteById(String id) throws Exception {
        Optional<Apolice> apolice = apoliceRepository.findById(id);
        if (apolice.isPresent()) {
            apoliceRepository.delete(apolice.get());
        } else {
            throw new Exception("Apolice não encontrado.");
        }
    }

}
