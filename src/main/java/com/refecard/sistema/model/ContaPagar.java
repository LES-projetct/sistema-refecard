package com.refecard.sistema.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class ContaPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorPagar;

    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    // RELAÇÃO N-1 COM FORNECEDOR
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    @JsonBackReference
    private Fornecedor fornecedor;
}