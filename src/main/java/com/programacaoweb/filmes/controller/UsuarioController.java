package com.programacaoweb.filmes.controller;

import com.programacaoweb.filmes.domain.Filme;
import com.programacaoweb.filmes.domain.Usuario;
import com.programacaoweb.filmes.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadUsuario")
    public String cadUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario-cadastro";
    }

    @PostMapping("/salvarUsuario")
    public String salvarUsuario(@ModelAttribute @Valid Usuario usuario, Errors errors, Model model) {
        this.usuarioService.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}


