package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class ContaReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorAberto;

    private LocalDate dataVencimento;

    private String statusPagamento;

    // RELAÇÃO N-1 COM CARTAO (várias contas para um cartão)
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    @JsonBackReference
    private Cartao cartao;

    // RELAÇÃO 1-1 COM VENDA (uma venda tem uma conta a receber)
    @OneToOne
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private Venda venda;
}