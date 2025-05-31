package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.Compra;
import com.via.agencia_viagens.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/nova")
    public Compra criarCompra(@RequestBody Compra compra) {
        return compraService.salvarCompra(compra);
    }

    @GetMapping("/{id}")
    public Compra buscarCompra(@PathVariable Long id) {
        return compraService.buscarPorId(id);
    }
}