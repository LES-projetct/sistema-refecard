package com.refecard.sistema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoListagemDTO {

    private Long id;

    private String produto;

    private String codigo;

    private Double valorCusto;

    private Double valorVenda;

    private String tipo;
}