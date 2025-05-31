package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.Transporte;
import com.via.agencia_viagens.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transporte")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @PostMapping("/cadastrar")
    public Transporte cadastrar(@RequestBody Transporte transporte) {
        return transporteService.salvarTransporte(transporte);
    }

    @GetMapping("/listar")
    public List<Transporte> listar() {
        return transporteService.listarTodos();
    }
}
