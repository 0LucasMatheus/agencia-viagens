package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.*;
import com.via.agencia_viagens.service.TransporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/transportes")
public class TransporteViewController {

    private final TransporteService transporteService;

    public TransporteViewController(TransporteService transporteService) {
        this.transporteService = transporteService;
    }

    @GetMapping
    public String listarTransportes(Model model) {
        model.addAttribute("transportes", transporteService.listarTodos());
        return "admin/lista-transportes";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoTransporte(Model model) {
        model.addAttribute("transporte", new Transporte() {
            @Override
            public String getTipo() {
                return "";
            }
        });
        return "admin/novo-transporte";
    }

    @PostMapping("/novo")
    public String salvarNovoTransporte(
            @RequestParam String tipo,
            @RequestParam String descricao,
            @RequestParam int quantidadeLugares,
            RedirectAttributes redirectAttributes) {

        Transporte transporte;
        if (tipo.equals("Ônibus")) {
            transporte = transporteService.criarOnibus(descricao, quantidadeLugares);
        } else {
            transporte = transporteService.criarAviao(descricao, quantidadeLugares);
        }

        redirectAttributes.addFlashAttribute("sucesso",
                "Transporte " + transporte.getTipo() + " criado com sucesso!");
        return "redirect:/admin/transportes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Transporte transporte = transporteService.buscarPorId(id);
        model.addAttribute("transporte", transporte);
        return "admin/editar-transporte";
    }

    @PostMapping("/editar/{id}")
    public String atualizarTransporte(
            @PathVariable Long id,
            @RequestParam String tipo,
            @RequestParam String descricao,
            @RequestParam int quantidadeLugares,
            RedirectAttributes redirectAttributes) {

        Transporte transporte;
        if (tipo.equals("Ônibus")) {
            transporte = new Onibus();
        } else {
            transporte = new Aviao();
        }

        transporte.setId(id);
        transporte.setDescricao(descricao);
        transporte.setQuantidadeLugares(quantidadeLugares);

        transporteService.salvarTransporte(transporte);

        redirectAttributes.addFlashAttribute("sucesso", "Transporte atualizado com sucesso!");
        return "redirect:/admin/transportes";
    }


    @GetMapping("/deletar/{id}")
    public String deletarTransporte(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        transporteService.deletarTransporte(id);
        redirectAttributes.addFlashAttribute("sucesso", "Transporte deletado com sucesso!");
        return "redirect:/admin/transportes";
    }
}