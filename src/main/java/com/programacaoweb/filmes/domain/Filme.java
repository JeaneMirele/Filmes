package com.programacaoweb.filmes.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O título deve ser preenchido")
    private String title;
    @NotBlank(message = "A descrição deve ser preenchida")
    private String description;
    @NotBlank(message = "O gênero deve ser preenchido")
    private String genre;
    @NotNull(message = "O preço não pode ser nulo")
    private Float price;
    @NotBlank(message = "O diretor deve ser preenchido")
    private String director;
    private LocalDate isDeleted;
    private String imageUrl;
}
