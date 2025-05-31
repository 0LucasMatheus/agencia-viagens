package com.via.agencia_viagens.model;

import java.math.BigDecimal;

public class GuiaTuristicoDecorator extends CompraDecorator {

    private String numeroGuia;

    public GuiaTuristicoDecorator(Compra compra, String numeroGuia) {
        super(compra);
        this.numeroGuia = numeroGuia;
    }

    @Override
    public BigDecimal getPrecoFinal() {
        return compra.getPreco().add(new BigDecimal("150.00"));
    }

    @Override
    public String getDescricaoFinal() {
        return compra.getDescricao() + " + Guia Turístico incluso";
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }
}
