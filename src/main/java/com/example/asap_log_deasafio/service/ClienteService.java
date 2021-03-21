package com.example.asap_log_deasafio.service;

import java.util.List;
import java.util.Optional;

import com.example.asap_log_deasafio.entity.Cliente;
import com.example.asap_log_deasafio.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> consultarClientes() {
        System.out.println("CHAMOU");
        return clienteRepository.findAll();
    }

    public Optional<Cliente> consultarClienteId(String id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente salvarCliente(Cliente cliente) {
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
