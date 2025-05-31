package com.via.agencia_viagens.model;

import java.time.LocalDate;

public class CalculoPrecoStrategyFactory {
    public static CalculoPrecoStrategy criarEstrategia(LocalDate dataIda) {
        int mes = dataIda.getMonthValue();

        if (mes == 11 || mes == 12 || mes == 1 || mes == 2 || mes == 6 || mes == 7) {
            return new AltaTemporadaStrategy();
        } else if (mes == 4 || mes == 9) {
            return new BaixaTemporadaStrategy();
        } else {
            return new PrecoNormalStrategy();
        }
    }
}

