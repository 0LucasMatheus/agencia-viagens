package com.via.agencia_viagens.model;

import java.math.BigDecimal;

public abstract class CompraDecorator {

    protected Compra compra;

    public CompraDecorator(Compra compra) {
        this.compra = compra;
    }

    public abstract BigDecimal getPrecoFinal();

    public abstract String getDescricaoFinal();

    public Compra getCompraOriginal() {
        return compra;
    }
}
