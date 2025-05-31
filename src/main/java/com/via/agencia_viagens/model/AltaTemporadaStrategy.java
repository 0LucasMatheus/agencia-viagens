package com.via.agencia_viagens.model;

public class AltaTemporadaStrategy implements CalculoPrecoStrategy {
    @Override
    public double calcularPreco(double precoBase) {
        return precoBase * 1.3;
    }
}

