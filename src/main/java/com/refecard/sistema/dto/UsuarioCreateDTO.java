package com.refecard.sistema.dto;

import com.refecard.sistema.model.usuario.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioCreateDTO {

    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private LocalDate dataNascimento;
    private Role role;
}