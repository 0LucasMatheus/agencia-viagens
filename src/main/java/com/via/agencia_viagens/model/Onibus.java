package com.via.agencia_viagens.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ONIBUS")
public class Onibus extends Transporte {

    @Override
    public String getTipo() {
        return "Ônibus";
    }
}
