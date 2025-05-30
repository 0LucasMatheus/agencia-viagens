package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.Cliente;
import com.via.agencia_viagens.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        System.out.println(cliente);
        return clienteRepository.save(cliente);
    }
}
