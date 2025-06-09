package com.programacaoweb.filmes.repository;

import com.programacaoweb.filmes.domain.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmesRepository extends JpaRepository<Filme, Long> {
    List<Filme> findAllByIsDeletedIsNull();
}
