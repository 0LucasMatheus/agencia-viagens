package com.via.agencia_viagens.repository;

import com.via.agencia_viagens.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Cliente, Long> {
}
