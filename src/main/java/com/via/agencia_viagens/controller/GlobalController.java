package com.via.agencia_viagens.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("clienteLogado")
    public Object adicionarClienteLogado(HttpSession session) {
        return session.getAttribute("clienteLogado");
    }
}
