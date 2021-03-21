package com.example.asap_log_deasafio.service;

import java.util.List;
import java.util.Optional;

import com.example.asap_log_deasafio.entity.Cliente;
import com.example.asap_log_deasafio.repository.ClienteRepository;
import com.example.asap_log_deasafio.util.UtilValidador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> consultarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> consultarClienteId(String id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Cliente cliente) throws Exception {
        if (clienteRepository.findById(cliente.getId()).isPresent()) {
            validarCliente(cliente);
            return clienteRepository.save(cliente);
        } else {
            throw new Exception("Este registro não existe no banco.");
        }

    }

    private void validarCliente(Cliente cliente) throws Exception {
        if (!UtilValidador.isValidString(cliente.getNome())) {
            throw new Exception("Nome do cliente obrigatório.");
        }

        if (!UtilValidador.isValidString(cliente.getCpf())) {
            throw new Exception("CPF obrigatório.");
        }

        if (UtilValidador.isCPF(cliente.getCpf())) {
            throw new Exception("CPF inválido.");
        }

        // Como vou enviar apenas a API para correção, a regra de CPF vai ser
        // implementada direto na API
        // a pesquisa muda quando estou editando o cpf, pois preciso ignorar o registro
        // que ja existente no banco
        List<Cliente> cpfCadastrados = UtilValidador.isValidString(cliente.getId())
                ? clienteRepository.findByCpfAndIdNot(cliente.getCpf(), cliente.getId())
                : clienteRepository.findByCpf(cliente.getCpf());
        if (cpfCadastrados.size() > 0) {
            throw new Exception("CPF já utilizado.");
        }

        if (!UtilValidador.isValidString(cliente.getCidade())) {
            throw new Exception("Cidade obrigatório.");
        }

        if (!UtilValidador.isValidString(cliente.getUf())) {
            throw new Exception("Estado obrigatório.");
        }

    }

    public Cliente salvarCliente(Cliente cliente) throws Exception {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public void deleteById(String id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
        } else {
            throw new Exception("Cliente não encontrado.");
        }
    }

}
