package com.programacaoweb.filmes.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome deve ser preenchido")
    @Column(unique = true)
    private String username;
    @Size(min = 4, message = "A senha deve conter no mínimo 4 caracteres")
    private String password;
    private Boolean isAdmin;

}
