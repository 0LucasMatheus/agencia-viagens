package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.CancelarCompraCommand;
import com.via.agencia_viagens.model.Compra;
import com.via.agencia_viagens.model.ConfirmarCompraCommand;
import com.via.agencia_viagens.model.DTO.CompraDTO;
import com.via.agencia_viagens.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/nova")
    public Compra criarCompra(@RequestBody CompraDTO dto) {
        return compraService.criarCompraViaDTO(dto);
    }


    @GetMapping("/{id}")
    public Compra buscarCompra(@PathVariable Long id) {
        return compraService.buscarPorId(id);
    }


    // Endpoint para confirmar compra
    @PostMapping("/{id}/confirmar")
    public String confirmarCompra(@PathVariable Long id) {
        compraService.executarCommand(id, new ConfirmarCompraCommand(compraService.buscarPorId(id)));
        return "Compra confirmada!";
    }

    // Endpoint para cancelar compra
    @PostMapping("/{id}/cancelar")
    public String cancelarCompra(@PathVariable Long id) {
        compraService.executarCommand(id, new CancelarCompraCommand(compraService.buscarPorId(id)));
        return "Compra cancelada!";
    }
}