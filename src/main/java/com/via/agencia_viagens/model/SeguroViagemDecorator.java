package com.via.agencia_viagens.model;

import java.math.BigDecimal;

public class SeguroViagemDecorator extends CompraDecorator {

    private boolean ativo;

    public SeguroViagemDecorator(Compra compra) {
        super(compra);
        this.ativo = true;
    }

    @Override
    public BigDecimal getPrecoFinal() {
        return compra.getPreco().add(new BigDecimal("200.00"));
    }

    @Override
    public String getDescricaoFinal() {
        return compra.getDescricao() + " + Seguro Viagem ativado";
    }

    public boolean isSeguroAtivo() {
        return ativo;
    }

    public void cancelarCompraComSeguro() {
        if (ativo) {
            compra.setStatus(Compra.StatusCompra.CANCELADA);
        }
    }
}
