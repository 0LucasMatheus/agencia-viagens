package com.via.agencia_viagens.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AVIAO")
public class Aviao extends Transporte {

    @Override
    public String getTipo() {
        return "Avião";
    }
}
