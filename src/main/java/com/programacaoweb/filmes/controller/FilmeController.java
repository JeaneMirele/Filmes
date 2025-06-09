package com.programacaoweb.filmes.controller;

import com.programacaoweb.filmes.domain.Filme;
import com.programacaoweb.filmes.service.FilmesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class FilmeController {
     @Autowired
     private FilmesService filmesService;

    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Filme filme, Errors errors) {
        if (errors.hasErrors()) {
            return "cadastro";
        } else {
             this.filmesService.save(filme);
            return "redirect:/admin"; // ajustar
        }
    }
}
