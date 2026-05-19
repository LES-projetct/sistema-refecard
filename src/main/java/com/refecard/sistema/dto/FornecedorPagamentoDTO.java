package com.refecard.sistema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FornecedorPagamentoDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private Double saldoDevedor;
}