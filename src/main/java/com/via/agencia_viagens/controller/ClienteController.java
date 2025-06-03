package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.Cliente;
import com.via.agencia_viagens.repository.ClienteRepository;
import com.via.agencia_viagens.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }

    @GetMapping("/perfil")
    public Cliente listar(@RequestBody Cliente cliente) {
        return clienteService.procurarId(cliente.getId());
    }
}

