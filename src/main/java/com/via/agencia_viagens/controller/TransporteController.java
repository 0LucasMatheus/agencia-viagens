package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.TransporteBase;
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
    public TransporteBase cadastrar(@RequestBody TransporteBase transporte) {
        return transporteService.salvarTransporte(transporte);
    }

    @GetMapping("/listar")
    public List<TransporteBase> listar() {
        return transporteService.listarTodos();
    }
}
