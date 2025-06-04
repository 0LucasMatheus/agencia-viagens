package com.via.agencia_viagens.model;

import java.math.BigDecimal;

public class BaixaTemporadaStrategy implements CalculoPrecoStrategy {
    private static final BigDecimal TAXA = new BigDecimal("0.8");

    @Override
    public BigDecimal calcularPreco(BigDecimal precoBase) {
        return precoBase.multiply(TAXA);
    }
}
