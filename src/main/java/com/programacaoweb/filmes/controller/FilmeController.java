package com.programacaoweb.filmes.controller;

import com.programacaoweb.filmes.domain.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.programacaoweb.filmes.service.FilmesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class FilmeController {
    @Autowired
    private FilmesService filmesService;

    @GetMapping
    public String index(Model model) {
        List<Filme> filmes = filmesService.findNotDeletedFilmes();
        model.addAttribute("filmes", filmes);
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Filme filme, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            if (filme.getId() == null) {
                return "cadastro";
            } else {
                model.addAttribute("filme", filme);
                return "editar";
            }
        }
        filmesService.save(filme);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Atualização salva com sucesso!");
        return "redirect:/admin";
    }

    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        try {
            Filme filme = filmesService.findById(id);
            model.addAttribute("filme", filme);
            return "editar";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("erro", ex.getMessage());
            return "erro";
        }

    }

    @GetMapping("/admin")
    public String admin(Model model) {
    List<Filme> filmes = filmesService.findAll();
    model.addAttribute("filmes", filmes);
        return "admin";
    }
    @GetMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Long id, Model model) {
        try {
            filmesService.delete(id);
            return "admin";
        } catch (RuntimeException ex) {
            model.addAttribute("erro", ex.getMessage());
            return "erro";
        }
    }

    @GetMapping("/restaurar/{id}")
    public String restaurarFilme(@PathVariable Long id, Model model) {
        try {
            filmesService.restore(id);
            return "admin";
        } catch (RuntimeException ex) {
            model.addAttribute("erro", ex.getMessage());
            return "erro";
        }
    }

}
