package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.Cliente;
import com.via.agencia_viagens.service.ClienteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cliente-view")
public class ClienteViewController {

    private final ClienteService clienteService;

    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/login")
    public String mostrarPaginaLogin(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "login";
    }

    @GetMapping("/cadastro")
    public String mostrarPaginaCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarCliente(@ModelAttribute Cliente cliente, Model model) {
        if (clienteService.existeClienteComEmail(cliente.getEmail())) {
            model.addAttribute("erro", "Já existe um cliente cadastrado com este email");
            return "cadastro";
        }

        clienteService.salvarCliente(cliente);
        model.addAttribute("sucesso", "Cadastro realizado com sucesso! Faça login para continuar.");
        return "login";
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model, Principal principal) {
        Cliente cliente = clienteService.buscarPorEmail(principal.getName());
        model.addAttribute("cliente", cliente);
        return "perfil";
    }

    @GetMapping("/editar")
    public String mostrarFormEdicao(Model model, Principal principal) {
        Cliente cliente = clienteService.buscarPorEmail(principal.getName());
        model.addAttribute("cliente", cliente);
        return "editar-perfil";
    }

    @PostMapping("/editar")
    public String atualizarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        clienteService.salvarCliente(cliente);
        redirectAttributes.addFlashAttribute("sucesso", "Perfil atualizado com sucesso!");
        return "redirect:/cliente-view/perfil";
    }

    @GetMapping("/logout")
    public String fazerLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/cliente-view/login";
    }
}