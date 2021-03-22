package com.example.asap_log_deasafio.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.concurrent.ThreadLocalRandom;

import com.example.asap_log_deasafio.entity.Apolice;
import com.example.asap_log_deasafio.entity.Cliente;
import com.example.asap_log_deasafio.helpers.ApoliceStatus;
import com.example.asap_log_deasafio.repository.ApoliceRepository;
import com.example.asap_log_deasafio.util.UtilGeral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApoliceService {

    @Autowired
    ApoliceRepository apoliceRepository;

    @Autowired
    ClienteService clienteService;

    public List<Apolice> consultarApolices() {
        return apoliceRepository.findAll();
    }

    public Optional<Apolice> consultarApoliceId(String id) {
        return apoliceRepository.findById(id);
    }

    public ApoliceStatus consultarResultadoApolice(Long numeroApolice) throws Exception {
        Optional<Apolice> apoliceOpt = apoliceRepository.findByNumeroApolice(numeroApolice);
        if (apoliceOpt.isPresent()) {
            return calcularStatusApolice(apoliceOpt.get());
        } else {
            throw new Exception("Insira um numero de apolice valido.");
        }
    }

    private ApoliceStatus calcularStatusApolice(Apolice apolice) {
        ApoliceStatus apoliceStatus = new ApoliceStatus();

        apoliceStatus.setApolice(apolice);

        Date dataAtual = new Date();
        apoliceStatus.setApoliceVencida(dataAtual.after(apolice.getDataFim()));

        apoliceStatus.setDiasVencidos(UtilGeral.calcularDiferencaDias(dataAtual, apolice.getDataFim()));

        // tambem adiciona os dias pra o vencimento
        apoliceStatus.setDiasParaVencimentos(apoliceStatus.getDiasVencidos() * -1);

        return apoliceStatus;
    }

    public Apolice atualizarApolice(Apolice apolice) throws Exception {
        Optional<Apolice> apoliceOpt = apoliceRepository.findById(apolice.getId());
        if (apoliceOpt.isPresent()) {
            validarApolice(apolice);
            // não deixa o usuario editar esse numero de apolice
            prepararApolice(apolice);
            apolice.setNumeroApolice(apoliceOpt.get().getNumeroApolice());
            return apoliceRepository.save(apolice);
        } else {
            throw new Exception("Este registro não existe no banco.");
        }

    }

    private void validarApolice(Apolice apolice) throws Exception {

        if (!UtilGeral.isValidString(apolice.getDataFim())) {
            throw new Exception("Data fim inválida.");
        }
        if (!UtilGeral.isValidString(apolice.getDataInicio())) {
            throw new Exception("Data inicio inválida.");
        }

        if (!UtilGeral.isValidString(apolice.getValor())) {
            throw new Exception("Valor obrigatório.");
        }

        if (!UtilGeral.isValidString(apolice.getPlaca())) {
            throw new Exception("Placa obrigatória.");
        }

        if (!UtilGeral.isPlacaValid(apolice.getPlaca())) {
            throw new Exception("Placa inválida.");
        }

        if (!UtilGeral.isValidString(apolice.getIdCliente())) {
            throw new Exception("Infome o cliente dono da apolice.");
        }

        if (apolice.getDataFim().before(apolice.getDataInicio())) {
            throw new Exception("Data inicio deve ser menor que data final.");
        }
    }

    private void gerarNumeroApolice(Apolice apolice) {

        // Gera o numero de apolice aleatorio e testa se está disponivel
        do {
            apolice.setNumeroApolice(ThreadLocalRandom.current().nextLong(10, 100000000));
        } while (apoliceRepository.findByNumeroApolice(apolice.getNumeroApolice()).isPresent());

    }

    private void prepararApolice(Apolice apolice) throws Exception {
        if (apolice.getId() == null) {
            // gera apenas o numero da apolice se for um registro novo
            gerarNumeroApolice(apolice);
        }
        Optional<Cliente> clienteOpt = clienteService.consultarClienteId(apolice.getIdCliente());
        if (clienteOpt.isPresent()) {
            apolice.setDono(clienteOpt.get());
        } else {
            throw new Exception("Cliente da apolice não encontrado.");
        }
    }

    public Apolice salvarApolice(Apolice apolice) throws Exception {
        validarApolice(apolice);
        prepararApolice(apolice);
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
