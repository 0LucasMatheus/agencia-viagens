package com.via.agencia_viagens.model;

import java.math.BigDecimal;

public class PrecoNormalStrategy implements CalculoPrecoStrategy {
    @Override
    public BigDecimal calcularPreco(BigDecimal precoBase) {
        return precoBase;
    }
}
