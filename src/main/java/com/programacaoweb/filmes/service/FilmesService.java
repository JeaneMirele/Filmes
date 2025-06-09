package com.programacaoweb.filmes.service;

import com.programacaoweb.filmes.domain.Filme;
import com.programacaoweb.filmes.repository.FilmesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FilmesService {
     private final FilmesRepository filmesRepository;
     private final FileStorageService fileStorageService;

     public FilmesService(FilmesRepository filmesRepository, FileStorageService
     fileStorageService) {
     this.filmesRepository = filmesRepository;
     this.fileStorageService = fileStorageService;
     }

     public void save(Filme filme) {
          if(filme.getId() == null) {
               fileStorageService.loadRandomFileRelativePath().ifPresent((p) -> {
                    filme.setImageUrl("/images/" + p.toString());
               });
          }else{
               Filme FilmImage = filmesRepository.findById(filme.getId()).orElseThrow();
               filme.setImageUrl(FilmImage.getImageUrl());
          }
          this.filmesRepository.save(filme);
     }

     public Filme findById(Long id) {
          return filmesRepository.findById(id).orElseThrow(() ->
                  new IllegalArgumentException("Filme Não Encontrado"));
     }

     public List<Filme> findNotDeletedFilmes() {
          return filmesRepository.findAllByIsDeletedIsNull();
     }
}
