package com.via.agencia_viagens.model;

// Comando para Cancelar Compra
public class CancelarCompraCommand implements CompraCommand {
    private Compra compra;

    public CancelarCompraCommand(Compra compra) {
        this.compra = compra;
    }

    @Override
    public void executar() {
        compra.setStatus(Compra.StatusCompra.CANCELADA);
    }
}
