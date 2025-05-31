package com.via.agencia_viagens.model.DTO;

import lombok.Data;

@Data
public class CompraDTO {
    private Long clienteId;
    private Long transporteIdaId;
    private Long transporteVoltaId; // pode ser null
    private String dataIda;
    private String dataVolta;
    private String numeroPassagem;
    private String hospedagem;
    private boolean possuiSeguro;
    private boolean possuiGuia;
}
