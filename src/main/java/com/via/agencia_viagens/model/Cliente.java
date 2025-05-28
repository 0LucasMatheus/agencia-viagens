package com.via.agencia_viagens.model;

import java.time.LocalDate;
import java.util.UUID;

public class Cliente {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String rg;
    private LocalDate dataNascimento;
    private String senha;

    public Cliente(String id, String nome, String cpf, String email, String rg, LocalDate dataNascimento, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getRg() { return rg; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getSenha() { return senha; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }

}
