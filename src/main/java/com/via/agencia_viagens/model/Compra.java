package com.via.agencia_viagens.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "transporte_ida_id")
    private Transporte transporteIda;

    @ManyToOne
    @JoinColumn(name = "transporte_volta_id")
    private Transporte transporteVolta;

    private LocalDate dataIda;
    private LocalDate dataVolta;
    private String numeroPassagem;

    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    private BigDecimal preco;
    private String hospedagem;
    private String descricao;


    private boolean possuiSeguro;
    private boolean possuiGuia;

    public enum StatusCompra {
        CONCLUIDA,
        CANCELADA,
        AGUARDANDO_PAGAMENTO
    }

    public String contatoGuia() {
        if (possuiGuia) {
            return "contato do Guia Turístico enviado para: " + this.cliente.getEmail();
        } else {
            return "Não possui Guia Turístico.";
        }
    }

    public void cancelarSeSeguroAtivo() {
        if (possuiSeguro) {
            this.status = StatusCompra.CANCELADA;
        } else {
            throw new IllegalStateException("Compra não possui seguro para permitir cancelamento especial.");
        }
    }

    public String getDescricaoDetalhada() {
        String desc = this.descricao != null ? this.descricao : "";
        if (possuiSeguro) desc += " + Seguro Viagem incluso";
        if (possuiGuia) desc += " + Guia Turístico incluso";
        return desc;
    }
}

