package com.via.agencia_viagens.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Viagem {
    private Long id;
    private String destino;
    private LocalDate dataIda;
    private LocalDate dataVolta;
    private String companhiaTransporte; // Pode ser aérea ou rodoviária
    private String hospedagem; // Pode ser null se for sem hospedagem
    private BigDecimal precoBase;
    private int vagas;
    private int reservasAtuais;

    public Viagem(Long id, String destino, LocalDate dataIda, LocalDate dataVolta,
                  String companhiaTransporte, String hospedagem, BigDecimal precoBase, int vagas) {
        this.id = id;
        this.destino = destino;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.companhiaTransporte = companhiaTransporte;
        this.hospedagem = hospedagem;
        this.precoBase = precoBase;
        this.vagas = vagas;
        this.reservasAtuais = 0;
    }

    public boolean verificarDisponibilidade() {
        return reservasAtuais < vagas;
    }

    public BigDecimal calcularPrecoBase() {
        return precoBase;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public LocalDate getDataIda() { return dataIda; }
    public void setDataIda(LocalDate dataIda) { this.dataIda = dataIda; }

    public LocalDate getDataVolta() { return dataVolta; }
    public void setDataVolta(LocalDate dataVolta) { this.dataVolta = dataVolta; }

    public String getCompanhiaTransporte() { return companhiaTransporte; }
    public void setCompanhiaTransporte(String companhiaTransporte) { this.companhiaTransporte = companhiaTransporte; }

    public String getHospedagem() { return hospedagem; }
    public void setHospedagem(String hospedagem) { this.hospedagem = hospedagem; }

    public BigDecimal getPrecoBase() { return precoBase; }
    public void setPrecoBase(BigDecimal precoBase) { this.precoBase = precoBase; }

    public int getVagas() { return vagas; }
    public void setVagas(int vagas) { this.vagas = vagas; }

    public int getReservasAtuais() { return reservasAtuais; }
    public void setReservasAtuais(int reservasAtuais) { this.reservasAtuais = reservasAtuais; }
}
