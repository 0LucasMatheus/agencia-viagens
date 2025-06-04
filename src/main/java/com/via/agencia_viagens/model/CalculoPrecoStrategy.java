package com.via.agencia_viagens.model;

import java.math.BigDecimal;

public interface CalculoPrecoStrategy {
    BigDecimal calcularPreco(BigDecimal precoBase);
}

