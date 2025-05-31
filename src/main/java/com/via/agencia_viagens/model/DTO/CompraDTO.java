package com.via.agencia_viagens.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompraDTO {
    private Long clienteId;
    private Long transporteIdaId;
    private Long transporteVoltaId; // pode ser null
    private LocalDate dataIda;
    private LocalDate dataVolta;
    private String numeroPassagem;
    private String hospedagem;
    private boolean possuiSeguro;
    private boolean possuiGuia;
}
