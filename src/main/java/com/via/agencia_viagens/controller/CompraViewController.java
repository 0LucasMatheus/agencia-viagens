package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.*;
import com.via.agencia_viagens.service.ClienteService;
import com.via.agencia_viagens.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/compra-view")
public class CompraViewController {

    private final CompraService compraService;
    private final ClienteService clienteService;

    public CompraViewController(CompraService compraService, ClienteService clienteService) {
        this.compraService = compraService;
        this.clienteService = clienteService;
    }

    @GetMapping("/nova")
    public String mostrarFormularioCompra(Model model) {
        model.addAttribute("compra", new Compra());
        model.addAttribute("transportes", compraService.listarTodosTransportes());
        return "nova-compra";
    }

    @PostMapping("/nova")
    public String processarCompra(
            @RequestParam Long clienteId,
            @RequestParam Long transporteIda,
            @RequestParam(required = false) Long transporteVolta,
            @RequestParam LocalDate dataIda,
            @RequestParam(required = false) LocalDate dataVolta,
            @RequestParam String hospedagem,
            @RequestParam(required = false, defaultValue = "false") boolean possuiSeguro,
            @RequestParam(required = false, defaultValue = "false") boolean possuiGuia,
            RedirectAttributes redirectAttributes) {

        Compra compra = compraService.criarCompra(
                clienteId, transporteIda, transporteVolta,
                dataIda, dataVolta, hospedagem, possuiSeguro, possuiGuia);

        redirectAttributes.addFlashAttribute("sucesso", "Compra realizada com sucesso!");
        return "redirect:/compra-view/detalhes/" + compra.getId();
    }

    @GetMapping("/detalhes/{id}")
    public String mostrarDetalhesCompra(@PathVariable Long id, Model model) {
        Compra compra = compraService.buscarPorId(id);
        model.addAttribute("compra", compra);
        return "detalhes-compra";
    }

    @GetMapping("/cliente/{clienteId}")
    public String listarComprasCliente(@PathVariable Long clienteId, Model model) {
        model.addAttribute("compras", compraService.listarComprasPorCliente(clienteId));
        return "lista-compras";
    }
}