package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.Cliente;
import com.via.agencia_viagens.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/login")
    public String fazerLogin(@ModelAttribute Cliente cliente, Model model, RedirectAttributes redirectAttributes) {
        Cliente clienteExistente = clienteService.buscarPorEmailESenha(cliente.getEmail(), cliente.getSenha());

        if (clienteExistente != null) {
            redirectAttributes.addFlashAttribute("cliente", clienteExistente);
            return "redirect:/cliente-view/perfil";
        } else {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }
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
    public String mostrarPerfil(@ModelAttribute("cliente") Cliente cliente, Model model) {
        if (cliente == null || cliente.getId() == null) {
            return "redirect:/cliente-view/login";
        }

        Cliente clienteCompleto = clienteService.procurarId(cliente.getId());
        model.addAttribute("cliente", clienteCompleto);
        return "perfil";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormEdicao(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.procurarId(id);
        model.addAttribute("cliente", cliente);
        return "editar-perfil";
    }

    @PostMapping("/editar/{id}")
    public String atualizarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        cliente.setId(id);
        clienteService.salvarCliente(cliente);
        redirectAttributes.addFlashAttribute("sucesso", "Perfil atualizado com sucesso!");
        return "redirect:/cliente-view/perfil";
    }

    @GetMapping("/logout")
    public String fazerLogout() {
        return "redirect:/cliente-view/login";
    }
}