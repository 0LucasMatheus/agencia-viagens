package com.via.agencia_viagens.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidadeLugares;

    private String descricao;

    public abstract String getTipo();
}
