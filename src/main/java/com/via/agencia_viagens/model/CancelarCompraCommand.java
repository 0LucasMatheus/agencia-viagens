package com.via.agencia_viagens.model;

public class CancelarCompraCommand implements CompraCommand {
    private Compra compra;

    public CancelarCompraCommand(Compra compra) {
        this.compra = compra;
    }

    @Override
    public void executar() {
        if (compra.possuiSeguro()) {
            compra.setStatus(Compra.StatusCompra.CANCELADA);
            compra.setSeguroAtivo(false); // Desativa o seguro ao cancelar
        } else {
            throw new IllegalStateException("Compra não possui seguro para cancelamento especial");
        }
    }
}
