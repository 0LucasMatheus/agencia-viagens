package com.via.agencia_viagens.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo" // nome do campo que vai indicar se é Onibus ou Aviao
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Onibus.class, name = "ONIBUS"),
        @JsonSubTypes.Type(value = Aviao.class, name = "AVIAO")
})
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
