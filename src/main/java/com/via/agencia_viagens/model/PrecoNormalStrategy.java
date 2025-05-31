package com.via.agencia_viagens.model;

public class PrecoNormalStrategy implements CalculoPrecoStrategy {
    @Override
    public double calcularPreco(double precoBase) {
        return precoBase;
    }
}
