package com.via.agencia_viagens.model;

import java.math.BigDecimal;

public class AltaTemporadaStrategy implements CalculoPrecoStrategy {
    private static final BigDecimal TAXA = new BigDecimal("1.3");

    @Override
    public BigDecimal calcularPreco(BigDecimal precoBase) {
        return precoBase.multiply(TAXA);
    }
}

