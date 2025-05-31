package com.via.agencia_viagens.model;

public class BaixaTemporadaStrategy implements CalculoPrecoStrategy {
    @Override
    public double calcularPreco(double precoBase) {
        return precoBase * 0.8;
    }
}
