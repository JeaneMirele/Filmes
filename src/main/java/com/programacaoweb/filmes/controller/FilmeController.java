package com.programacaoweb.filmes.controller;

import com.programacaoweb.filmes.domain.Filme;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeController {
    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }

    @PostMapping("/save")
    public String cadastro(@ModelAttribute @Valid Filme filme, Errors errors) {
        if (errors.hasErrors()){
            return "cadastro";
        }else {
            return "redirect:/cadastro"; // ajustar
        }
    }
}
