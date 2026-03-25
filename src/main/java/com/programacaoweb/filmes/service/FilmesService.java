package com.programacaoweb.filmes.service;

import com.programacaoweb.filmes.domain.Filme;
import com.programacaoweb.filmes.repository.FilmesRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FilmesService {
     private final FilmesRepository filmesRepository;
     private final FileStorageService fileStorageService;

     public FilmesService(FilmesRepository filmesRepository, FileStorageService
     fileStorageService) {
     this.filmesRepository = filmesRepository;
     this.fileStorageService = fileStorageService;
     }

     public void save(Filme filme, MultipartFile imagemFile) {
          if (imagemFile != null && !imagemFile.isEmpty()) {

               String nomeGerado = fileStorageService.saveFile(imagemFile);
               filme.setImageUrl("/images/" + nomeGerado);
          } else if (filme.getId() == null) {
               fileStorageService.loadRandomFileRelativePath().ifPresent(p -> {
                    filme.setImageUrl("/images/" + p.toString());
               });
          } else {
               Filme antigo = filmesRepository.findById(filme.getId()).orElseThrow();
               filme.setImageUrl(antigo.getImageUrl());
          }
          filmesRepository.saveAndFlush(filme);
     }

     public Filme findById(Long id) {
          return filmesRepository.findById(id).orElseThrow(() ->
                  new IllegalArgumentException("Filme Não Encontrado"));
     }

    public List<Filme> findAll() {
         List<Filme> filmes = filmesRepository.findAll();
         return filmes;
    }

     public List<Filme> findNotDeletedFilmes() {
          return filmesRepository.findAllByIsDeletedIsNull();
     }

     public void delete(Long id) {
          Optional<Filme> filme = filmesRepository.findById(id);

          if (!filme.isPresent()) {
               throw new RuntimeException("Filme não encontrada com o ID: " + id);
          }
          Filme f = filme.get();
          if (f.getIsDeleted() != null) {
               throw new RuntimeException("O filme de id = " + id + " já foi deletado");
          }
          f.setIsDeleted(LocalDate.now());
          filmesRepository.save(f);
     }

     public void restore(Long id) {
          Optional<Filme> filme = filmesRepository.findById(id);
          if (!filme.isPresent()) {
               throw new RuntimeException("Filme não encontrada com o ID: " + id);
          }
          Filme f = filme.get();
          if (f.getIsDeleted() == null) {
               throw new RuntimeException("O filme de id = " + id + " já está ativo");
          }
          f.setIsDeleted(null);
          filmesRepository.save(f);
     }
}
