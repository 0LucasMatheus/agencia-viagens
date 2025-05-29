package com.via.agencia_viagens.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Onibus extends TransporteBase {

    @Override
    public String getTipo() {
        return "Ônibus";
    }
}
