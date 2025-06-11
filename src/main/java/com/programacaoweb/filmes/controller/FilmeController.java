package com.programacaoweb.filmes.controller;

import com.programacaoweb.filmes.domain.Filme;
import com.programacaoweb.filmes.domain.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.programacaoweb.filmes.service.FilmesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;



@Controller
public class FilmeController {
    @Autowired
    private FilmesService filmesService;

    @GetMapping
    public String index(Model model, HttpSession session, Authentication authentication) {
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }
        model.addAttribute("username", username);
        List<Filme> filmes = filmesService.findNotDeletedFilmes();
        model.addAttribute("filmes", filmes);
        model.addAttribute("carrinhoQtd", getCarrinhoQtd(session));
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Filme filme, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        boolean isUpdate = filme.getId() != null;

        if (errors.hasErrors()) {
            if (!isUpdate) {
                return "redirect:/cadastro";
            } else {
                model.addAttribute("filme", filme);
                return "redirect:/editar";
            }
        }
        filmesService.save(filme);
        if (!isUpdate) {
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cadastro realizado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Atualização realizada com sucesso!");
        }
        return "redirect:/admin";
    }

    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model, HttpSession session, Authentication authentication) {
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }
        model.addAttribute("username", username);

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
    public String admin(Model model, HttpSession session, Authentication authentication) {
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }
        model.addAttribute("username", username);
        List<Filme> filmes = filmesService.findAll();
        model.addAttribute("filmes", filmes);
        model.addAttribute("carrinhoQtd", getCarrinhoQtd(session));
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "admin";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Long id, Model model) {
        try {
            filmesService.delete(id);
            return "redirect:/admin";
        } catch (RuntimeException ex) {
            model.addAttribute("erro", ex.getMessage());
            return "erro";
        }
    }

    @GetMapping("/restaurar/{id}")
    public String restaurarFilme(@PathVariable Long id, Model model) {
        try {
            filmesService.restore(id);
            return "redirect:/admin";
        } catch (RuntimeException ex) {
            model.addAttribute("erro", ex.getMessage());
            return "erro";
        }
    }

    private Long getCarrinhoQtd(HttpSession session) {
        List<Filme> carrinho = (List<Filme>) session.getAttribute("carrinho");
        if (carrinho == null)
            return 0L;
        return (long) carrinho.size();
    }

    @GetMapping("/adicionarCarrinho/{id}")
    public String adicionarCarrinho(@PathVariable Long id, HttpSession session) {
        Filme filme = filmesService.findById(id);
        List<Filme> carrinho = (List<Filme>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.add(filme);
        session.setAttribute("carrinho", carrinho);
        return "redirect:/";
    }

    @GetMapping("/verCarrinho")
    public String verCarrinho(HttpSession session, Model model, RedirectAttributes redirectAttributes, Authentication authentication) {
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }
        model.addAttribute("username", username);

        List<Filme> carrinho = (List<Filme>) session.getAttribute("carrinho");

        if (carrinho == null || carrinho.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemError", "Não existem itens no carrinho");
            return "redirect:/";
        }

        model.addAttribute("carrinho", carrinho);
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "carrinho";
    }

    @GetMapping("/finalizarCompra")
    public String finalizarCompra(HttpSession session,RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Compra finalizada com sucesso!");
        return "redirect:/";
    }

}


