package com.via.agencia_viagens.service;

import com.via.agencia_viagens.model.Cliente;
import com.via.agencia_viagens.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente procurarId(Long clienteId) {
        return clienteRepository.findById(clienteId).orElse(null);
    }

    public Cliente buscarPorEmailESenha(String email, String senha) {
        return clienteRepository.findByEmailAndSenha(email, senha);
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existeClienteComEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }
}