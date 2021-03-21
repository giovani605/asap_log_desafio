package com.example.asap_log_deasafio.service;

import java.util.List;
import java.util.Optional;

import com.example.asap_log_deasafio.entity.Apolice;
import com.example.asap_log_deasafio.repository.ApoliceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApoliceService {

    @Autowired
    ApoliceRepository apoliceRepository;

    public List<Apolice> consultarApolices() {
        return apoliceRepository.findAll();
    }

    public Optional<Apolice> consultarApoliceId(Long id) {
        return apoliceRepository.findById(id);
    }

    public Apolice atualizarApolice(Apolice apolice) throws Exception {
        if (apoliceRepository.findById(apolice.getNumeroApolice()).isPresent()) {
            validarApolice(apolice);
            return apoliceRepository.save(apolice);
        } else {
            throw new Exception("Este registro não existe no banco.");
        }

    }

    private void validarApolice(Apolice apolice) throws Exception {

    }

    public Apolice salvarApolice(Apolice apolice) throws Exception {
        validarApolice(apolice);
        return apoliceRepository.save(apolice);
    }

    public void deleteById(Long id) throws Exception {
        Optional<Apolice> apolice = apoliceRepository.findById(id);
        if (apolice.isPresent()) {
            apoliceRepository.delete(apolice.get());
        } else {
            throw new Exception("Apolice não encontrado.");
        }
    }

}
