package com.via.agencia_viagens.controller;

import com.via.agencia_viagens.model.Cliente;
import com.via.agencia_viagens.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
    public String fazerLogin(@ModelAttribute Cliente cliente, Model model,
                             HttpSession session, RedirectAttributes redirectAttributes) {
        Cliente clienteExistente = clienteService.buscarPorEmailESenha(cliente.getEmail(), cliente.getSenha());

        if (clienteExistente != null) {
            session.setAttribute("clienteLogado", clienteExistente);
            return "redirect:/compra-view/minhas-compras";
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
    public String cadastrarCliente(@ModelAttribute Cliente cliente, Model model,
                                   HttpSession session) {
        if (clienteService.existeClienteComEmail(cliente.getEmail())) {
            model.addAttribute("erro", "Já existe um cliente cadastrado com este email");
            return "cadastro";
        }

        Cliente novoCliente = clienteService.salvarCliente(cliente);
        session.setAttribute("clienteLogado", novoCliente);
        return "redirect:/compra-view/minhas-compras";
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");
        if (clienteLogado == null) {
            return "redirect:/cliente-view/login";
        }

        Cliente clienteCompleto = clienteService.procurarId(clienteLogado.getId());
        model.addAttribute("cliente", clienteCompleto);
        return "perfil";
    }

    @GetMapping("/editar")
    public String mostrarFormEdicao(HttpSession session, Model model) {
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");
        if (clienteLogado == null) {
            return "redirect:/cliente-view/login";
        }

        Cliente clienteCompleto = clienteService.procurarId(clienteLogado.getId());
        model.addAttribute("cliente", clienteCompleto);
        return "editar-perfil";
    }

    @PostMapping("/editar")
    public String atualizarCliente(@ModelAttribute Cliente cliente,
                                   HttpSession session, RedirectAttributes redirectAttributes) {
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");
        if (clienteLogado == null) {
            return "redirect:/cliente-view/login";
        }

        cliente.setId(clienteLogado.getId());
        Cliente clienteAtualizado = clienteService.salvarCliente(cliente);
        session.setAttribute("clienteLogado", clienteAtualizado);
        redirectAttributes.addFlashAttribute("sucesso", "Perfil atualizado com sucesso!");
        return "redirect:/cliente-view/perfil";
    }

    @GetMapping("/logout")
    public String fazerLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/cliente-view/login";
    }
}