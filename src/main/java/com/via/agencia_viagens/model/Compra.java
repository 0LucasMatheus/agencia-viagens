package com.via.agencia_viagens.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Cliente cliente;

    // Transporte de ida (obrigatório)
    @ManyToOne
    @JoinColumn(name = "transporte_ida_id")
    private Transporte transporteIda;

    // Transporte de volta (opcional)
    @ManyToOne
    @JoinColumn(name = "transporte_volta_id")
    private Transporte transporteVolta;

    private String dataIda;
    private String dataVolta;

    private String numeroPassagem;

    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    private BigDecimal preco;

    private String hospedagem;

    private String descricao;


    @Transient
    private List<CompraDecorator> extras;

    public enum StatusCompra {
        CONCLUIDA,
        CANCELADA,
        AGUARDANDO_PAGAMENTO
    }
}

