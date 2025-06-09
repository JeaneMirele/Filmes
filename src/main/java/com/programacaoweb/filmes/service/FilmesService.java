package com.programacaoweb.filmes.service;

import com.programacaoweb.filmes.domain.Filme;
import com.programacaoweb.filmes.repository.FilmesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
          fileStorageService.loadRandomFileRelativePath().ifPresent((p) -> {
               filme.setImageUrl(p.toString());
          });

          this.filmesRepository.save(filme);
     }
}
