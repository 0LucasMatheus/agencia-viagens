package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.*;
import com.via.agencia_viagens.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
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
    public String mostrarFormularioCompra(Model model, Principal principal) {
        Cliente cliente = clienteService.buscarPorEmail(principal.getName());

        model.addAttribute("compra", new Compra());
        model.addAttribute("transportes", compraService.listarTodosTransportes());
        model.addAttribute("hospedagens", List.of("Hotel Standard", "Hotel Premium", "Resort", "Pousada"));
        model.addAttribute("clienteId", cliente.getId());
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
        return "redirect:/compra-view/minhas-compras";
    }

    @GetMapping("/minhas-compras")
    public String listarComprasCliente(Principal principal, Model model) {
        Cliente cliente = clienteService.buscarPorEmail(principal.getName());
        model.addAttribute("compras", compraService.listarComprasPorCliente(cliente.getId()));
        return "lista-compras";
    }

    @PostMapping("/confirmar/{id}")
    public String confirmarCompra(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        compraService.confirmarCompra(id);
        redirectAttributes.addFlashAttribute("sucesso", "Compra confirmada com sucesso!");
        return "redirect:/compra-view/minhas-compras";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarCompra(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            compraService.cancelarCompra(id);
            redirectAttributes.addFlashAttribute("sucesso", "Compra cancelada com sucesso!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/compra-view/minhas-compras";
    }

    @GetMapping("/contato-guia/{id}")
    public String obterContatoGuia(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        String mensagem = compraService.obterContatoGuia(id);
        redirectAttributes.addFlashAttribute("info", mensagem);
        return "redirect:/compra-view/minhas-compras";
    }
}