package com.refecard.sistema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioListagemDTO {

    private Long id;

    private String nome;

    private String codigoCartaoRfid;

    private Double saldo;

    private String status;
}