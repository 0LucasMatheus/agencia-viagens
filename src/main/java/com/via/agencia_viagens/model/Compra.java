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


    //private boolean possuiSeguro; mudando a forma de implemntar os decorators
    //private boolean possuiGuia;

    private String numeroGuia; // null se não tiver guia
    private Boolean seguroAtivo; // null se não tiver seguro

    public boolean possuiGuia() {
        return numeroGuia != null;
    }

    public boolean possuiSeguro() {
        return seguroAtivo != null && seguroAtivo;
    }

    public String contatoGuia() {
        return possuiGuia() ?
                "Contato do Guia Turístico (" + numeroGuia + ") enviado para: " + this.cliente.getEmail() :
                "Não possui Guia Turístico.";
    }

    public String getDescricaoDetalhada() {
        StringBuilder desc = new StringBuilder();
        if (descricao != null) desc.append(descricao);
        if (possuiSeguro()) desc.append(" + Seguro Viagem");
        if (possuiGuia()) desc.append(" + Guia Turístico");
        return desc.toString();
    }
    public enum StatusCompra {
        CONCLUIDA,
        CANCELADA,
        AGUARDANDO_PAGAMENTO
    }

}

