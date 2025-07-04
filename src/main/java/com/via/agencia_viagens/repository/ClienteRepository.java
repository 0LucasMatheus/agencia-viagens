package com.via.agencia_viagens.repository;

import com.via.agencia_viagens.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByEmailAndSenha(String email, String senha);
    Cliente findByEmail(String email);
    boolean existsByEmail(String email);
}