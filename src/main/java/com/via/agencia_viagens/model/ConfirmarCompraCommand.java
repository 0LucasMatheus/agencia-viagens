package com.via.agencia_viagens.model;

// Comando para Confirmar Compra
public class ConfirmarCompraCommand implements CompraCommand {
    private Compra compra;

    public ConfirmarCompraCommand(Compra compra) {
        this.compra = compra;
    }

    @Override
    public void executar() {
        compra.setStatus(Compra.StatusCompra.CONCLUIDA);
    }
}

