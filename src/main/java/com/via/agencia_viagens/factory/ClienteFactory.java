package com.via.agencia_viagens.factory;

import com.via.agencia_viagens.model.Cliente;

import java.time.LocalDate;
import java.util.UUID;

public class ClienteFactory {

    public static Cliente criarCliente(String nome, String cpf, String email, String rg, LocalDate dataNascimento, String senha) {
        String idGerado = UUID.randomUUID().toString();
        return new Cliente(idGerado, nome, cpf, email, rg, dataNascimento, senha);
    }
}
