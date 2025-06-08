package com.programacaoweb.filmes.controller;


import com.programacaoweb.filmes.domain.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmeController {
    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        Filme filme = new Filme(1L,"A viagem de Chihiro", "Chihiro e seus pais estão se mudando para uma cidade diferente. A caminho da nova casa, o pai decide pegar um atalho. Eles se deparam com uma mesa repleta de comida, embora ninguém esteja por perto. Chihiro sente o perigo, mas seus pais começam a comer. Quando anoitece, eles se transformam em porcos. Agora, apenas Chihiro pode salvá-los.", "Ficção científica", 39.90f,
                "Hayao Miyazaki",null, "/img/Chihiro.jpg");

        model.addAttribute("filme", filme);
        model.addAttribute("count", 1);
        return "index";
    }
}
